package com.js.worker.code.netty.server;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBufferOutputStream;

public class RpcMessage extends BaseMessage{
	
	private byte[] param;

	public RpcMessage(byte info, String uuid, String serviceName, byte[] extend, byte[] param) {
		super(RPC_MESSAGE, info, uuid, serviceName, extend);
		this.param = param;
	}
	
	public RpcMessage(String uuid, String serviceName, byte[] param) {
		this((byte) 0, uuid, serviceName, null, param);
	}

	public byte[] param() {
		return param;
	}
	
	public int length() {
		return super.length() + param.length;
	}
	
	public void write(ChannelBufferOutputStream bout) throws IOException {
		super.write(bout);
		bout.write(param);
	}
	
	@Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(super.toString()).append("{ ");
    	sb.append("Info(binary): ").append(Integer.toBinaryString(info)).append(", ");
    	sb.append("uuid: ").append(uuid).append(", ");
    	sb.append("serviceName: ").append(serviceName).append(", ");
    	sb.append("extend: ").append(new String(extend)).append(", ");
    	sb.append("params: ").append(new String(param));
    	sb.append(" }");
    	return sb.toString();
    }

}
