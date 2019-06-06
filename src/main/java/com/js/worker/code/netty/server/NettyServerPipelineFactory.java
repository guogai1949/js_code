package com.js.worker.code.netty.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class NettyServerPipelineFactory implements ChannelPipelineFactory{
	
	private NettyServer nettyServer;
	
	public NettyServerPipelineFactory(NettyServer nettyServer) {
		this.nettyServer = nettyServer;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		// Create a default pipeline implementation.
        ChannelPipeline pipeline = Channels.pipeline();

        // Decoder
//        pipeline.addLast("decoder", new NettyMessageDecoder());
      pipeline.addLast("decoder", new StringDecoder());
        // Encoder
//        pipeline.addLast("encoder", new NettyMessageEncoder());
      pipeline.addLast("encoder", new StringEncoder());
        // business logic.
        pipeline.addLast("handler", new NettyServerHandler(nettyServer));

        return pipeline;
	}

}
