package com.im.server.handler;

import com.im.sdk.protocal.Message;
import com.im.sdk.protocal.Message.Data;

import io.netty.channel.ChannelHandlerContext;

public class HeatBeatHandler implements IRequestHandler {

	@Override
	public void hand(ChannelHandlerContext ctx, Data data) {
		System.out.println("HeatBeatHandler=================>>");
		ctx.writeAndFlush(data);
	}

	@Override
	public int getCmd() {
		return Message.Data.Cmd.HEARTBEAT_VALUE;
	}

}
