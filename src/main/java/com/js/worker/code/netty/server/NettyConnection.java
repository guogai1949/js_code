package com.js.worker.code.netty.server;

import java.util.List;

import org.jboss.netty.channel.Channel;

public interface NettyConnection {
	
	/**
     * (flags != 1) synchronously (flags==1) asynchronously
     * 
     * @param flags
     * @return
     */
    public Object recv(Integer taskId, int flags);

    /**
     * In the new design, receive flow is through registerQueue, then push message into queue
     * 
     * @param recvQueu
     */
    public void markUuidToChannel(String uuid, Channel channel);
  
    public Channel channelByUuid(String uuid);

    public void enqueue(RpcMessage message, Channel channel);
    
    public RpcMessage fetchResultMessage();

    public void send(List<RpcMessage> messages);

    public void send(RpcMessage message);

    public boolean available();

    /**
     * close this connection
     */
    public void close();

    public boolean isClosed();

    public void cleanup();

}
