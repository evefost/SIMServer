package com.im.protocol.handler;

import com.im.sdk.protocol.Message.Data;
import com.im.sdk.protocol.Message.Data.Cmd;
import com.im.server.core.ProtocolHandler;

import io.netty.channel.ChannelHandlerContext;

public class BindClientHandler implements ProtocolHandler {

	@Override
	public void handleRequest(ChannelHandlerContext ctx, Data data) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCmd() {
		return Cmd.BIND_CLIENT_VALUE;
	}

}
