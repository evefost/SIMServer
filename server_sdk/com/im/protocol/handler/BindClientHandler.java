package com.im.protocol.handler;

import com.im.sdk.protocol.Message.Data;
import com.im.sdk.protocol.Message.Data.Cmd;
import com.im.server.core.IMSession;
import com.im.server.core.IMSession.ClientInfo;
import com.im.server.core.ProtocolHandler;
import com.im.server.util.SessionUtils;

import io.netty.channel.ChannelHandlerContext;

public class BindClientHandler extends ProtocolHandler {

	@Override
	public void handleRequest(ChannelHandlerContext ctx, Data data) {
		ClientInfo clientInfo = IMSession.buildClientInfo();
		clientInfo.setBindTime(System.currentTimeMillis());
		clientInfo.setId(data.getClientId());
		clientInfo.setIp(ctx.channel().remoteAddress().toString());
		IMSession newSession = new IMSession(ctx.channel(),clientInfo);
		getSessionManager().addSession(newSession);
		SessionUtils.reply(newSession, data.getCmd());
	}

	@Override
	public int getCmd() {
		return Cmd.BIND_CLIENT_VALUE;
	}

}
