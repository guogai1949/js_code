package com.js.worker.code.netty;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.js.worker.code.netty.client.NettyClient;
import com.js.worker.code.netty.server.Option;
import com.js.worker.code.netty.server.RpcMessage;

public class NettyClientDemo {

	public static void main(String[] args) throws Exception {
		Map conf = new HashMap();
		InetAddress host = InetAddress.getByName("127.0.0.1");
		NettyClient client = new NettyClient(host, 18000, conf);
		client.start();
		Thread.sleep(5000);
		RpcMessage msg = new RpcMessage(Option.OPTIONS,"123","test",null,"holloWorld\n123123".getBytes());
		client.send(msg);
	}

}
