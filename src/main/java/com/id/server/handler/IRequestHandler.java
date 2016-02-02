package com.id.server.handler;

import com.im.sdk.protocal.Message;

import io.netty.channel.ChannelHandlerContext;

public interface IRequestHandler {

	public void hand(ChannelHandlerContext ctx, Message.Data data);
	
	public int getCmd();
}
