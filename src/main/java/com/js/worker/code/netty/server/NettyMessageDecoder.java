package com.js.worker.code.netty.server;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyMessageDecoder extends FrameDecoder {

	private static final Logger LOG = LoggerFactory.getLogger(NettyMessageDecoder.class);
    public final Charset CHARSET = Charset.forName("UTF-8");
    
    public NettyMessageDecoder() {
		super();
	}

	/*
     * 协议
	 * rpc消息 :  消息总长度(4Byte）+  uuid长度(1Byte)+服务名长度(1Byte)+扩展长度(2Byte)  +  消息类型（1Byte）+预留位（1Byte）+  uuid+servicename+扩展字段  +  params
	 * 登录消息:  消息总长度(4Byte）+  uuid长度(1Byte)+服务名长度(1Byte)+扩展长度(2Byte)  +  消息类型（1Byte）+预留位（1Byte）+  uuid+servicename+扩展字段  +  密文
	 * 登录应答:  消息总长度(4Byte）+  uuid长度(1Byte)+服务名长度(1Byte)+扩展长度(2Byte)  +  消息类型（1Byte）+预留位（1Byte）+  uuid+servicename+扩展字段  +  登录结果（1Byte）
	 * ack消息 :  消息总长度(4Byte）+  uuid长度(1Byte)+服务名长度(1Byte)+扩展长度(2Byte)  +  消息类型（1Byte）+预留位（1Byte）+  uuid+servicename+扩展字段 
	 * 注解：
	 * 消息类型：rpc消息=0x10，登录消息=0x20，登录应答=0x30，ack消息=0x40，其他
	 * 扩展字段：长度为0就没有
	 * 登录结果：成功=1，失败=0
     */
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buf) throws Exception {
        // Make sure that we have received at least a short
        int available = buf.readableBytes();
        if (available < 10) {
            // need more data
            return null;
        }
        try {
            // Mark the current buffer position before reading task/len field
            // because the whole frame might not be in the buffer yet.
            // We will reset the buffer position to the marked position if
            // there's not enough bytes in the buffer.
            buf.markReaderIndex();
            
            int total = buf.readInt();
            if(available < total) {
            	buf.resetReaderIndex();
                return null;
            }
//------------------------------------------------------------------------------ 
            int uuidLength =  buf.readUnsignedByte();
            int serviceNameLength = buf.readUnsignedByte();
            int extendLength = buf.readUnsignedShort();
            
            byte type = buf.readByte();
            byte info = buf.readByte();
            
            String uuid = buf.readBytes(uuidLength).toString(CHARSET);

            String serviceName = buf.readBytes(serviceNameLength).toString(CHARSET);
            
            byte[] extend = new byte[extendLength];
            buf.readBytes(extend);
            
            switch (type) {
			case 0x10:	{	//rpc message
	            int paramLength = total - 10 - uuidLength - serviceNameLength - extendLength;
	            byte[] param = new byte[paramLength];
	            buf.readBytes(param);

	            RpcMessage ret = new RpcMessage(info, uuid, serviceName, extend, param);
	            LOG.debug("received rpc message, {}", ret);
	            return ret;
			}
			case 0x20: {	//login message
//	            int ciphertextLength = total - 10 - uuidLength - serviceNameLength - extendLength;
//	            byte[] ciphertext = new byte[ciphertextLength];
//	            buf.readBytes(ciphertext);
//	            
//	            String password = (String) conf.get("drpc.service.password");
//	            byte[] plaintext = aes.decrypt(ciphertext, password);
//
//	            LOG.debug("service: {}, password: {}", serviceName, new String(plaintext, CHARSET));
//	            
//	            LoginMessage ret = new LoginMessage(info, uuid, serviceName, extend, plaintext);
//	            LOG.debug("Received login message, {}", ret.toString());
	            return null;
			}
			case 0x30: {	//login reply message
//				byte result = buf.readByte();
//				LoginReplyMessage ret = new LoginReplyMessage(info, uuid, serviceName, extend, result);
//				LOG.debug("Received login reply message, {}", ret.toString());
				return null;
			}
			case 0x40: {	//ack message
//				AckMessage ret = new AckMessage(info, uuid, serviceName, extend);
////				LOG.debug("Received ack message, {}", ret.toString());
				return null;
			}
			default:
				LOG.error("Received a unknow message!!!");
				break;
			}
            return null;
        } catch(Exception e) {
        	LOG.error("Decode error!", e);
        	throw e;
        } finally {
        }

    }
}
