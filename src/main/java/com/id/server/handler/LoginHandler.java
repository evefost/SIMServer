package com.id.server.handler;

import java.util.Random;

import com.im.sdk.protocal.Message;
import com.im.sdk.protocal.Message.Data;

import io.netty.channel.ChannelHandlerContext;

public class LoginHandler implements IRequestHandler {

	@Override
	public void hand(ChannelHandlerContext ctx, Data msg) {
		
		Message.Data.Builder reply = Message.Data.newBuilder();
		reply.setCmd(Message.Data.Cmd.LOGIN_VALUE);
		reply.setCreateTime(msg.getCreateTime());
		reply.setAccount(msg.getAccount());
		boolean ok = new Random().nextBoolean();
		reply.setLoginSuccess(ok);
		if (ok) {
			System.out.println("channelRead 登录成功,回应客户端:" + msg.getAccount());
			reply.setContent("登录成功");
		} else {
			reply.setContent("登录失败:xxxx");
			System.out.println("channelRead 登录失败,回应客户端:" + msg.getAccount());
		}
		ctx.writeAndFlush(reply);

	}

	@Override
	public int getCmd() {
		return Message.Data.Cmd.LOGIN_VALUE;
	}

}
