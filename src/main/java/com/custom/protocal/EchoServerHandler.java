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
package com.custom.protocal;


import com.custom.util.StringUtils;
import com.im.sdk.protocal.Message;
import com.im.sdk.protocal.Message.Data;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class EchoServerHandler extends ChannelHandlerAdapter {

	/**
	 * Creates a client-side handler.
	 */
	public EchoServerHandler() {

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {

		System.out.println("channelActive 已连上服务器 发送聊天服务地址给客户端 ");
		Message.Data.Builder data = Message.Data.newBuilder();
		data.setCmd(Message.Data.Cmd.LOGIN_VALUE);
		data.setCreateTime(System.currentTimeMillis());
		data.setContent("我是服务端_请登录，60秒内不登录将被断开");
		data.setIp("192.168.1.38");
		data.setPort(345678);
		ctx.writeAndFlush(data);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		
		// ctx.write(msg);
		Message.Data data = (Data) msg;
		int i = Message.Data.Cmd.CHAT_MESSAGE_VALUE;
		System.out.println("channelRead 收到消息[" + data.getId() + "]content[" + data.getContent());
		if(data.getCmd() == Message.Data.Cmd.LOGIN_VALUE&& !StringUtils.isEmpty(data.getAccount())){
			//登录成功,回应客户端
			
			System.out.println("channelRead 登录成功,回应客户端:"+data.getAccount());
			ctx.writeAndFlush(data);
		}
		if(data.getCmd() == Message.Data.Cmd.HEARTBEAT_VALUE){
			System.out.println("channelRead 心跳 回应客户端:"+data.getAccount());
			ctx.writeAndFlush(data);
		}
		
		if(data.getCmd() == Message.Data.Cmd.CHAT_MESSAGE_VALUE){
			System.out.println("channelRead  回应客户端 已收到消息:");
			Message.Data.Builder reply = Message.Data.newBuilder();
			reply.setCmd(Message.Data.Cmd.CHAT_MESSAGE_ECHO_VALUE);
			reply.setCreateTime(data.getCreateTime());
			ctx.writeAndFlush(reply);
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
		System.out.println("channelActive 服务器断开");
	}
}
