package com.js.worker.code.netty.server;

import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ChildChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServerHandler extends SimpleChannelUpstreamHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(NettyServerHandler.class);
	
	private NettyServer server;

	public NettyServerHandler(NettyServer nettyServer) {
		this.server = nettyServer;
	}

	@Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        LOG.info("Connection established {}", e.getChannel().getRemoteAddress());
        server.addChannel(e.getChannel());
    }

    @Override
    public void childChannelClosed(ChannelHandlerContext ctx, ChildChannelStateEvent e) throws Exception {
        super.childChannelClosed(ctx, e);
        LOG.info("Connection closed {}", e.getChildChannel().getRemoteAddress());
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
        LOG.info("Connection channelDisconnected {}", e.getChannel().getRemoteAddress());
    };

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
        LOG.info("Connection channelClosed {}", e.getChannel().getRemoteAddress());

        server.removeChannel(e.getChannel());
    };

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
//    	long receviceTime = System.nanoTime();
    	
        Object msg = e.getMessage();
        if (msg == null)
            return;       
     
        // enqueue the received message for processing
        try {
            server.enqueue((RpcMessage) msg, e.getChannel());
        } catch (Exception ex) {
            LOG.warn("Failed to enqueue a request message" + ex.toString(), ex);
        }
        
//        NIOTest.setTestTime("receviceTime", receviceTime);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        if (e.getChannel() != null) {
            LOG.info("Channel occur exception {}", e.getChannel().getRemoteAddress());
        }

        server.closeChannel(e.getChannel());
    }
}
