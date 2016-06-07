package com.im.server.core;

import com.im.sdk.protocol.Message;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author xieyang
 *
 */
public interface ProtocolHandler {

	public void handleRequest(ChannelHandlerContext ctx, Message.Data data);
	
	public int getCmd();
}
