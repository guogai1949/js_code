package com.js.worker.code.netty.server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.worker.code.utils.Utils;


public class NettyServer implements NettyConnection {
	
	private static final Logger LOG = LoggerFactory.getLogger(NettyServer.class);
	
    private ExecutorService cachedThreadPool = null;
    
    final ChannelFactory factory;
    
    final ServerBootstrap bootstrap;
    
    private int port;
    
    volatile ChannelGroup allChannels = new DefaultChannelGroup("nio-server");
	
	public NettyServer() throws Exception {
		port = 18000;
		this.cachedThreadPool = Executors.newCachedThreadPool();
		ThreadFactory bossFactory = new NettyRenameThreadFactory("server" + "-boss");
        ThreadFactory workerFactory = new NettyRenameThreadFactory("server" + "-worker");
		factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(bossFactory), Executors.newCachedThreadPool(workerFactory));
        bootstrap = new ServerBootstrap(factory);
        bootstrap.setOption("reuserAddress", true);
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.receiveBufferSize", 5242880);
        bootstrap.setOption("child.keepAlive", true);
        
        bootstrap.setPipelineFactory(new NettyServerPipelineFactory(this));
        
        // Bind and start to accept incoming connections.
        Channel channel = null;
		while (channel == null) {
			try {
				channel = bootstrap.bind(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), port));
			} catch (Exception e) {
				port = Utils.retry(port);
				Utils.sleepMs(1000);
			} 
		}
		LOG.info("success start netty server !!!! channel:{}", channel);
		allChannels.add(channel);
	}

	@Override
	public Object recv(Integer taskId, int flags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void markUuidToChannel(String uuid, Channel channel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Channel channelByUuid(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enqueue(RpcMessage message, Channel channel) {
//    	LOG.info("uuid: {}, time: {}", message.uuid(), System.currentTimeMillis() - Long.parseLong(new String(message.message())));
    	
    	if((message.info() & Option.RETURN) == 0)
    		channel.write(message);
		
	}

	@Override
	public RpcMessage fetchResultMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send(List<RpcMessage> messages) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(RpcMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean available() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	public void removeChannel(Channel channel) {
		// TODO Auto-generated method stub
		
	}

	public void closeChannel(Channel channel) {
		// TODO Auto-generated method stub
		
	}

	public void addChannel(Channel channel) {
		// TODO Auto-generated method stub
		
	}

}
