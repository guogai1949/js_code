package com.js.worker.code.netty.server;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

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
        pipeline.addLast("decoder", new NettyMessageDecoder());
        // Encoder
        pipeline.addLast("encoder", new NettyMessageEncoder());
        // business logic.
        pipeline.addLast("handler", new NettyServerHandler(nettyServer));

        return pipeline;
	}

}
