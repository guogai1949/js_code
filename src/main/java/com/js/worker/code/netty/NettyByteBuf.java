package com.js.worker.code.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
 
public class NettyByteBuf {
	public static void main(String[] args) {
 
		// 实例初始化
		ByteBuf buffer = Unpooled.buffer(20);
		
		System.out.println(String.format(
				"#####初始化 capacity:%s readerIndex:%s writeIndex:%s readeableBytes:%s writableBytes:%s",
				buffer.capacity(), buffer.readerIndex(), buffer.writerIndex(),buffer.readableBytes(),buffer.writableBytes()));
 
		// 写数据
		String msg = "xy";
		buffer.writeBytes(msg.getBytes());
		System.out.println(String.format(
				"#####写数据 capacity:%s readerIndex:%s writeIndex:%s readeableBytes:%s writableBytes:%s",
				buffer.capacity(), buffer.readerIndex(), buffer.writerIndex(),buffer.readableBytes(),buffer.writableBytes()));
 
		// 读数据（只读取一个字节）
		// byte[] vArray = new Byte[ buffer.writerIndex()];
		byte[] vArray = new byte[1];
		buffer.readBytes(vArray);
		System.out.println("result is " + new String(vArray));
		System.out.println(String.format(
				"#####读数据 capacity:%s readerIndex:%s writeIndex:%s readeableBytes:%s writableBytes:%s",
				buffer.capacity(), buffer.readerIndex(), buffer.writerIndex(),buffer.readableBytes(),buffer.writableBytes()));
 
		// discardReadBytes
//		buffer.discardReadBytes();
		buffer.resetReaderIndex();
		System.out.println(String.format(
				"#####discard capacity:%s readerIndex:%s writeIndex:%s readeableBytes:%s writableBytes:%s",
				buffer.capacity(), buffer.readerIndex(), buffer.writerIndex(),buffer.readableBytes(),buffer.writableBytes()));
 
		// clear
		buffer.clear();
		System.out.println(String.format(
				"#####clear capacity:%s readerIndex:%s writeIndex:%s readeableBytes:%s writableBytes:%s",
				buffer.capacity(), buffer.readerIndex(), buffer.writerIndex(),buffer.readableBytes(),buffer.writableBytes()));
		
	}
}