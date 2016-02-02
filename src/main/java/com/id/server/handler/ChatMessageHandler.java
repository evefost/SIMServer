package com.id.server.handler;

import com.im.sdk.protocal.Message;
import com.im.sdk.protocal.Message.Data;

import io.netty.channel.ChannelHandlerContext;

public class ChatMessageHandler implements IRequestHandler {

	@Override
	public void hand(ChannelHandlerContext ctx, Data data) {
		System.out.println("channelRead  普通消息:"+data.getContent()+"==time:"+data.getCreateTime());
		Message.Data.Builder reply = Message.Data.newBuilder();
		reply.setCmd(Message.Data.Cmd.CHAT_MESSAGE_ECHO_VALUE);
		reply.setCreateTime(data.getCreateTime());
		ctx.writeAndFlush(reply);
		
	}

	@Override
	public int getCmd() {
		return Message.Data.Cmd.CHAT_MESSAGE_VALUE;
	}

}
