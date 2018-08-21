package com.js.worker.code.netty.client;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.worker.code.netty.server.NettyRenameThreadFactory;
import com.js.worker.code.utils.DcfClientConfig;
import com.js.worker.code.utils.Utils;

public class NettyClient implements IConnection {
	
		private static final Logger LOG = LoggerFactory.getLogger(NettyClient.class);

	    protected String name;

	    protected final int max_retries;
	    protected final int base_sleep_ms;
	    protected final int max_sleep_ms;
	    protected final long timeoutMs;
	    protected final int MAX_SEND_PENDING;
	    
	    protected final InetAddress host;
	    protected final int port;

	    protected AtomicInteger retries;

	    protected AtomicReference<Channel> channelRef;
	    protected ClientBootstrap bootstrap;
	    protected final InetSocketAddress remote_addr;
	    protected final ChannelFactory clientChannelFactory;

	    protected final int buffer_size;
	    protected final AtomicBoolean being_closed;

	    protected AtomicLong pendings;
	    protected int messageBatchSize;

	    protected ScheduledExecutorService scheduler;

	    protected ReconnectRunnable reconnector;

	    protected Set<Channel> closingChannel;

	    protected AtomicBoolean isConnecting = new AtomicBoolean(false);

	    protected Map conf;

	    protected final Object channelClosing = new Object();
	    
	    protected volatile boolean init;

	    @SuppressWarnings("rawtypes")
	    public NettyClient(InetAddress host, int port, Map conf) {
	    	this.host = host;
	    	this.port = port;
	    	this.conf = conf;
	        retries = new AtomicInteger(0);
	        channelRef = new AtomicReference<Channel>(null);
	        being_closed = new AtomicBoolean(false);
	        pendings = new AtomicLong(0);

	        // Configure
	        buffer_size = DcfClientConfig.getStormMessagingNettyBufferSize(conf);
	        max_retries = Math.min(30, DcfClientConfig.getStormMessagingNettyMaxRetries(conf));
	        base_sleep_ms = DcfClientConfig.getStormMessagingNettyMinSleepMs(conf);
	        max_sleep_ms = DcfClientConfig.getStormMessagingNettyMaxSleepMs(conf);

	        timeoutMs = DcfClientConfig.getNettyPendingBufferTimeout(conf);
	        MAX_SEND_PENDING = (int) DcfClientConfig.getNettyMaxSendPending(conf);

	        this.messageBatchSize = DcfClientConfig.getStormNettyMessageBatchSize(conf);
	        
	        ThreadFactory bossFactory = new NettyRenameThreadFactory("NettyClientboss");
	        ThreadFactory workerFactory = new NettyRenameThreadFactory("NettyClientworker");
	        clientChannelFactory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(bossFactory), Executors.newCachedThreadPool(workerFactory));
	        
	        scheduler = Executors.newScheduledThreadPool(5, new NettyRenameThreadFactory("client-schedule-service"));
	        
	        reconnector = new ReconnectRunnable();
	        Thread reconnectThread = new Thread(reconnector);
	        reconnectThread.setDaemon(true);
	        reconnectThread.setPriority(Thread.MIN_PRIORITY);
	        reconnectThread.start();
	        
	        // Start the connection attempt.
	        remote_addr = new InetSocketAddress(host, port);
	        name = remote_addr.toString();

	        closingChannel = new HashSet<Channel>();
	    }
	    
	    public void start() throws Exception {
	        bootstrap = new ClientBootstrap(clientChannelFactory);
	        bootstrap.setOption("tcpNoDelay", true);
	        bootstrap.setOption("reuserAddress", true);
	        bootstrap.setOption("sendBufferSize", buffer_size);
	        bootstrap.setOption("keepAlive", true);

	        // Set up the pipeline factory.
	        bootstrap.setPipelineFactory(new NettyClientPipelineFactory(this));
	        reconnect();
	    }

		public void reconnect() {
			reconnector.pushEvent(this);
		}
		
		public void doReconnect() {
	        if (channelRef.get() != null) {

	            // if (channelRef.get().isWritable()) {
	            // LOG.info("already exist a writable channel, give up reconnect, {}",
	            // channelRef.get());
	            // return;
	            // }
	            return;
	        }

	        if (isClosed() == true) {
	            return;
	        }

	        if (isConnecting.getAndSet(true)) {
	            LOG.info("Connect twice {}", name);
	            return;
	        }
	        
	        final long sleepMs = getSleepTimeMs();
	        LOG.info("Reconnect ... [{}], {}, sleep {}ms", retries.get(), name, sleepMs);
	        ChannelFuture future = bootstrap.connect(remote_addr);
	        future.addListener(new ChannelFutureListener() {
	            public void operationComplete(ChannelFuture future) throws Exception {
	                isConnecting.set(false);
	                Channel channel = future.getChannel();
	                if (future.isSuccess()) {
	                    // do something else
	                    LOG.info("Connection established, channel = :{}", channel);
//	                    sendLgoinMessage(channel);
	                    setChannel(channel);
	                    init = true;
	                    // handleResponse();
	                } else {
	                    LOG.info("Failed to reconnect ... [{}], {}, channel = {}, cause = {}", retries.get(), name, channel, future.getCause());
	                    Utils.sleepMs(sleepMs);
	                    reconnect();
	                }
	            }
	        });
	    }
		
	    void setChannel(Channel newChannel) {
	        final Channel oldChannel = channelRef.getAndSet(newChannel);

	        if (newChannel != null) {
	            retries.set(0);
	        }

	        final String oldLocalAddres = (oldChannel == null) ? "null" : oldChannel.getLocalAddress().toString();
	        String newLocalAddress = (newChannel == null) ? "null" : newChannel.getLocalAddress().toString();
	        LOG.info("Use new channel {} replace old channel {}", newLocalAddress, oldLocalAddres);

	        // avoid one netty client use too much connection, close old one
	        if (oldChannel != newChannel && oldChannel != null) {

	            closeChannel(oldChannel);
	            LOG.info("Successfully close old channel " + oldLocalAddres);
	            // scheduler.schedule(new Runnable() {
	            //
	            // 
	            // public void run() {
	            //
	            // }
	            // }, 10, TimeUnit.SECONDS);

	            // @@@ todo
	            // pendings.set(0);
	        }
	    }
	    
	    /**
	     * Avoid channel double close
	     *
	     * @param channel
	     */
	    void closeChannel(final Channel channel) {
	        synchronized (channelClosing) {
	            if (closingChannel.contains(channel)) {
	                LOG.info(channel.toString() + " is already closed");
	                return;
	            }

	            closingChannel.add(channel);
	        }

	        LOG.debug(channel.toString() + " begin to closed");
	        ChannelFuture closeFuture = channel.close();
	        closeFuture.addListener(new ChannelFutureListener() {
	            public void operationComplete(ChannelFuture future) throws Exception {

	                synchronized (channelClosing) {
	                    closingChannel.remove(channel);
	                }
	                LOG.debug(channel.toString() + " finish closed");
	            }
	        });
	    }
	
		@Override
		public Object recv(Integer taskId, int flags) {
			// TODO Auto-generated method stub
			return null;
		}
	
		@Override
		public void registerRecvQueue(Integer taskId, BlockingQueue<Object> recvQueu) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void registerSendQueue(Integer taskId, BlockingQueue<Object> sendQueu) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void enqueue(Object message) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void send(List<Object> messages) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void send(Object message) {

	        // throw exception if the client is being closed
	        if (isClosed()) {
	            LOG.warn("Client is being closed, and does not take requests any more");
	            throw new RuntimeException("Channel is closed, Fail send message!");
//	            return;
	        }

	        try {
	        	Channel channel = channelRef.get();
	            if (channel == null) {
	            	LOG.debug("Handle failed message!");
	            	throw new RuntimeException("Channel is null, Fail send message!");
//	                handleFailedChannel(message);
//	                return;
	            }
	            
	        	flushRequest(channel, message);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        } finally {
	        	
	        }
	    
			
		}
	
		private void flushRequest(Channel channel, Object requests) {
	        if (requests == null)
	            return;
	        
	        pendings.incrementAndGet();

	        ChannelFuture future = channel.write(requests);
	        future.addListener(new ChannelFutureListener() {
	            public void operationComplete(ChannelFuture future) throws Exception {
	                pendings.decrementAndGet();
	                if (!future.isSuccess()) {
	                    Channel channel = future.getChannel();
	                    if (isClosed() == false) {
	                        LOG.info("Failed to send requests to " + name + ": " + channel.toString() + ":", future.getCause());
	                    }

	                    if (null != channel) {
	                        exceptionChannel(channel);
	                    }
	                } else {
//	                	send();
//	                     LOG.debug("request(s) sent");
	                }
	            }
	        });
	    
			
		}

		@Override
		public boolean available() {
			// TODO Auto-generated method stub
			return false;
		}
	
		@Override
		public void setLogin() {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void close() {
			this.init = false;
	    	LOG.info("Close netty connection to {}", name);
	        if (!being_closed.compareAndSet(false, true)) {
	            LOG.info("Netty client has been closed.");
	            return;
	        }

	        Channel channel = channelRef.get();
	        if (channel == null) {
	            LOG.info("Channel {} has been closed before", name);
	            return;
	        }

	        if (channel.isWritable()) {
//	            MessageBatch toBeFlushed = messageBatchRef.getAndSet(null);
//	            flushRequest(channel, toBeFlushed);
	        }

	        // wait for pendings to exit
	        final long timeoutMilliSeconds = 10 * 1000;
	        final long start = System.currentTimeMillis();

	        LOG.info("Waiting for pending batchs to be sent with " + name + "..., timeout: {}ms, pendings: {}", timeoutMilliSeconds, pendings.get());

	        while (pendings.get() != 0) {
	            try {
	                long delta = System.currentTimeMillis() - start;
	                if (delta > timeoutMilliSeconds) {
	                    LOG.error("Timeout when sending pending batchs with {}..., there are still {} pending batchs not sent", name, pendings.get());
	                    break;
	                }
	                Thread.sleep(1000); // sleep 1s
	            } catch (InterruptedException e) {
	                break;
	            }
	        }

	        close_n_release();
			
		}
	
		private void close_n_release() {
	        if (channelRef.get() != null) {
	            setChannel(null);
	        }
		}

		@Override
		public boolean isClosed() {
			// TODO Auto-generated method stub
			return false;
		}
		
	    /**
	     * # of milliseconds to wait per exponential back-off policy
	     */
	    private int getSleepTimeMs() {
	        int sleepMs = base_sleep_ms * retries.getAndIncrement();
	        if (sleepMs > max_sleep_ms) {
	            sleepMs = max_sleep_ms;
	        }
	        return sleepMs;
	    }

		public AtomicBoolean getBeing_closed() {
			// TODO Auto-generated method stub
			return being_closed;
		}

		public Object getRemoteAddr() {
			return remote_addr;
		}

		public void disconnectChannel(Channel channel) {
	        if (isClosed()) {
	            return;
	        }

	        if (channel == channelRef.get()) {
	            setChannel(null);
	            reconnect();
	        } else {
	            closeChannel(channel);
	        }
	    }

		public void exceptionChannel(Channel channel) {
	        if (channel == channelRef.get()) {
	            setChannel(null);
	        } else {
	            closeChannel(channel);
	        }
	    }
}
