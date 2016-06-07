package com.im.protocol.handler;

import com.im.sdk.protocol.Message;
import com.im.sdk.protocol.Message.Data;
import com.im.server.core.ProtocolHandler;

import io.netty.channel.ChannelHandlerContext;

public class HeatBeatHandler implements ProtocolHandler {

	@Override
	public void handleRequest(ChannelHandlerContext ctx, Data data) {
		System.out.println("HeatBeatHandler=================>>");
		ctx.writeAndFlush(data);
	}

	@Override
	public int getCmd() {
		return Message.Data.Cmd.HEARTBEAT_VALUE;
	}

}
