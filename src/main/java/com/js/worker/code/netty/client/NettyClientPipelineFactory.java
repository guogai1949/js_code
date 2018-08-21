package com.js.worker.code.netty.client;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

import com.js.worker.code.netty.server.NettyMessageDecoder;
import com.js.worker.code.netty.server.NettyMessageEncoder;

public class NettyClientPipelineFactory implements ChannelPipelineFactory {
	
	private NettyClient nettyClient;

	public NettyClientPipelineFactory(NettyClient nettyClient) {
		this.nettyClient = nettyClient;
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
        pipeline.addLast("handler", new NettyClientHandler(nettyClient));

        return pipeline;
	}

}
