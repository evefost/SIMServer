package com.im.server.core;

import com.im.sdk.protocal.Message;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author xieyang
 *
 */
public interface ServerHandler {

	public void hand(ChannelHandlerContext ctx, Message.Data data);
	
	public int getCmd();
}
