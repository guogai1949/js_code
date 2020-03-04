package com.js.worker.code.netty.server;

import java.io.IOException;
import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.buffer.ChannelBuffers;

public class BaseMessage {
	public static final byte RPC_MESSAGE 			= 0x10;
	public static final byte LOGIN_MESSAGE 			= 0x20;
	public static final byte LOGIN_REPLY_MESSAGE 	= 0x30;
	public static final byte ACK_MESSAGE 			= 0x40;
	
	protected byte type;
	protected byte info;
	
	protected String uuid;
	protected String serviceName;
	protected byte[] extend;
	
	public BaseMessage(byte type, byte info, String uuid, String serviceName, byte[] extend) {
		super();
		this.type = type;
		this.info = info;
		this.uuid = uuid;
		this.serviceName = serviceName;
		this.extend = extend;
	}

	public int length() {
		return 10 + uuidLength() + serviceNameLength() + extendLength();
	}

	protected int uuidLength() {
		return uuid.length();
	}

	protected int serviceNameLength() {
		return serviceName.length();
	}

	protected int extendLength() {
		return extend == null ? 0 : extend.length;
	}

	public byte type() {
		return type;
	}

	public byte info() {
		return info;
	}

	public String uuid() {
		return uuid;
	}

	public String serviceName() {
		return serviceName;
	}

	public byte[] extend() {
		return extend;
	}
	
	public ChannelBuffer buffer() throws Exception {
		ChannelBufferOutputStream bout = new ChannelBufferOutputStream(ChannelBuffers.directBuffer(length()));
//		ChannelBuffer cb = ChannelBuffers.directBuffer(length());
		write(bout);
//		write(cb);
        bout.close();
        return bout.buffer();
	}

	protected void write(ChannelBufferOutputStream bout) throws IOException {
		bout.writeInt(length());
		bout.writeByte(uuidLength());
		bout.writeByte(serviceNameLength());
		bout.writeShort(extendLength());
		
		bout.writeByte(type);
		bout.writeByte(info);
		
		bout.writeBytes(uuid);
		bout.writeBytes(serviceName);
		
		if(extend != null)
			bout.write(extend);
	}
	
	protected void write(ChannelBuffer bout) throws IOException {
		bout.writeInt(length());
		bout.writeByte(uuidLength());
		bout.writeByte(serviceNameLength());
		bout.writeShort(extendLength());
		
		bout.writeByte(type);
		bout.writeByte(info);
		
		bout.writeBytes(uuid.getBytes(Charset.forName("UTF-8")));
		bout.writeBytes(serviceName.getBytes(Charset.forName("UTF-8")));
		
		if(extend != null)
			bout.writeBytes(extend);
	}

}
