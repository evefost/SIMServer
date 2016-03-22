/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.im.server.core;

import com.im.sdk.protocal.Message;
import com.im.sdk.protocal.Message.Data;
import com.im.sdk.protocal.Message.Data.Cmd;
import com.im.server.handler.IRequestHandler;
import com.im.server.util.HandllerUtil;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class IMServerHandler extends ChannelHandlerAdapter {

	/**
	 * Creates a client-side handler.
	 */
	public IMServerHandler() {

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {

		System.out.println("channelActive 已连上服务器 发送聊天服务地址给客户端 :"+ctx.channel().remoteAddress());
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

		Message.Data data = (Data) msg;
		showMessageInfoLog(ctx, data);
		IRequestHandler handler = HandllerUtil.getIRequestHandler(data.getCmd());
		System.out.println("channelRead handler :" + handler);
		if (handler != null) {
			handler.hand(ctx, data);
		}

	}

	private void showMessageInfoLog(ChannelHandlerContext ctx, Message.Data data) {
		switch (data.getCmd()) {
		case Cmd.LOGIN_VALUE:
			System.out.println("channelRead 登录消息 :" + ctx.channel().remoteAddress());
			break;
		case Cmd.LOGOUT_VALUE:
			System.out.println("channelRead 登出消息 :" + ctx.channel().remoteAddress());
			break;
		case Cmd.CHAT_MSG_VALUE:
//			System.out.println("channelRead  普通消息:"+data.getContent()+"==time:"+data.getCreateTime());
			break;
		case Cmd.HEARTBEAT_VALUE:
			System.out.println("channelRead  心跳消息:");
			break;
		case Cmd.BIND_DEVICE_VALUE:
			System.out.println("channelRead  绑定device:");
			break;
		default:
			break;
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.out.println("exceptionCaught 异常关闭");
		cause.printStackTrace();
		ctx.close();
	}

	public void channelInactive(ChannelHandlerContext ctx) {
		System.out.println("channelInactive 客户端断开:"+ctx.channel().remoteAddress());
	}
}
