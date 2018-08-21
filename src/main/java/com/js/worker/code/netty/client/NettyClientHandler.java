package com.js.worker.code.netty.client;

import java.net.ConnectException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClientHandler extends SimpleChannelUpstreamHandler {
    private static final Logger LOG = LoggerFactory.getLogger(NettyClientHandler.class);
    private NettyClient client;
    private AtomicBoolean being_closed;

    NettyClientHandler(NettyClient client) {
        this.client = client;
        being_closed = client.getBeing_closed();
    }

    /**
     * @@@ Comment this function
     * 
     * Don't allow call from low netty layer, whose call will try to obtain the lock of jstorm netty layer
     * otherwise it will lead to deadlock 
     */
//    
//    public void channelInterestChanged(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
//        
//    	client.notifyInterestChanged(e.getChannel());
//    }

    /**
     * Sometime when connect one bad channel which isn't writable, it will call this function
     */
    
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent event) {
        // register the newly established channel
        Channel channel = event.getChannel();
        LOG.info("connection established to :{}, local port:{}", client.getRemoteAddr(), channel.getLocalAddress());
    }

    
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) {
    	Object msg = event.getMessage();
    	
    	LOG.debug("Received a message, {}", msg);
    	
        client.enqueue(msg);;
    }

    /**
     * 
     * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#exceptionCaught(org.jboss.netty.channel.ChannelHandlerContext,
     *      org.jboss.netty.channel.ExceptionEvent)
     */
    
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent event) {
        Throwable cause = event.getCause();
        if (being_closed.get() == false) {
            if (!(cause instanceof ConnectException)) {
                LOG.info("Connection failed:" + client.getRemoteAddr(), cause);
            }

            client.exceptionChannel(event.getChannel());
            client.reconnect();
        }
    }

    /**
     * Attention please,
     * 
     * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#channelDisconnected(org.jboss.netty.channel.ChannelHandlerContext,
     *      org.jboss.netty.channel.ChannelStateEvent)
     */
    
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        LOG.info("Receive channelDisconnected to {}, channel = {}", client.getRemoteAddr(), e.getChannel());
        // ctx.sendUpstream(e);
        super.channelDisconnected(ctx, e);

        client.disconnectChannel(e.getChannel());
    }

    
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        LOG.info("Connection to {} has been closed, channel = {}", client.getRemoteAddr(), e.getChannel());
        super.channelClosed(ctx, e);
    }

}
