package com.js.worker.code.netty.server;

public class Option {
	public static final byte RETURN 	= 0x01;
	public static final byte REALTIME 	= 0x02;
	public static final byte ACK 		= 0x04;
	
	public static final byte OPTIONS 	= 0x00;
	
	private byte options;
	
	public void setReturn(boolean isReturn) {
		if(isReturn)
			options = (byte) (options & ~RETURN);
		else
			options = (byte) (options | RETURN);
	}
	
	public void setRealtime(boolean isRealtime) {
		if(isRealtime)
			options = (byte) (options & ~REALTIME);
		else
			options = (byte) (options | REALTIME);
	}
	
	public void setAck(boolean isAck) {
		if(isAck)
			options = (byte) (options | ACK);
		else
			options = (byte) (options & ~ACK);
	}
	
	public byte getOptions() {
		return options;
	}
}
