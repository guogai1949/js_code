package com.js.worker.code.netty.server;

import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyMessageEncoder extends OneToOneEncoder {

	private static final Logger LOG = LoggerFactory.getLogger(NettyMessageEncoder.class);
	
    public NettyMessageEncoder() {
		super();
	}

	
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object obj) throws Exception {
    	try {
    		if(obj instanceof RpcMessage) {
    			return ((RpcMessage) obj).buffer();
    		}
    		
//    		if(obj instanceof AckMessage){
//    			return ((AckMessage) obj).buffer();
//    		}
//    		
//    		if(obj instanceof LoginMessage) {
//    			return ((LoginMessage) obj).buffer(aes);
//    		}
//    		
//    		if(obj instanceof LoginReplyMessage) {
//    			return ((LoginReplyMessage) obj).buffer();
//    		}
    		
    		throw new RuntimeException("Unsupported encoding of object of class " + obj.getClass().getName());
		} catch (Exception e) {
			LOG.error("encode error!!!");
			throw e;
		}
    }



}
