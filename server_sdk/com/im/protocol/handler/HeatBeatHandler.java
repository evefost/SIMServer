package com.im.protocol.handler;

import com.im.sdk.protocol.Message;
import com.im.sdk.protocol.Message.Data;
import com.im.server.core.ProtocolHandler;
import com.im.server.util.XXTEA;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandlerContext;

public class HeatBeatHandler extends ProtocolHandler {

	@Override
	public void handleRequest(ChannelHandlerContext ctx, Data data) {
		System.out.println("HeatBeatHandler=================>>ct:"+data.getContent());
		try {
              String content ="abc";
              String key = "123";
              //byte[] enBytes = XXTEA.encrypt(ByteString.copyFromUtf8(data.getContent()).toByteArray(),key.getBytes());
              //byte[] deBytes = XXTEA.decrypt(ByteString.copyFromUtf8(data.getContent()).toByteArray(),key.getBytes());
//              System.out.println("src content===:"+new String(deBytes));
//              
//              byte[] deBytes1 = XXTEA.decrypt(data.getContent().getBytes(),key.getBytes());
//              byte[] deBytes2 = XXTEA.decrypt(data.getContent().getBytes(),key.getBytes());
              //System.out.println("deBytes1:"+deBytes1);
              String dstring = new String (XXTEA.decrypt(data.getBody().toByteArray(), key));
              System.out.println("decrpt:"+dstring);
		} catch (Exception e) {
			System.out.println("Ex:"+e.toString());
		}
		//byte[] de = XXTEA.decrypt(data.getContent().getBytes(), "123".getBytes());
		//System.out.println("HeatBeatHandler=================>>:"+new String(de));
		ctx.writeAndFlush(data);
	}

	@Override
	public int getCmd() {
		return Message.Data.Cmd.HEARTBEAT_VALUE;
	}

}
