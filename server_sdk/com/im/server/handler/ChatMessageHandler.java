package com.im.server.handler;

import com.im.sdk.protocal.Message;
import com.im.sdk.protocal.Message.Data;
import com.im.sdk.protocal.Message.Data.Cmd;
import com.im.server.core.IMSession;
import com.xie.im.session.manager.ContextHolder;
import com.xie.im.session.manager.SessionManager;

import io.netty.channel.ChannelHandlerContext;

public class ChatMessageHandler implements IRequestHandler {

	@Override
	public void hand(ChannelHandlerContext ctx, Data data) {
		System.out.println("channelRead  普通消息:"+data.getContent()+"==time:"+data.getCreateTime());
		//先保存消息，用户收到才删除
		saveMessage(data);
		//回应客户端
		Message.Data.Builder reply = Message.Data.newBuilder();
		reply.setCmd(Cmd.CHAT_MSG_ECHO_VALUE);
		reply.setCreateTime(data.getCreateTime());
		ctx.writeAndFlush(reply);
		
		SessionManager sessionManager = ((SessionManager) ContextHolder
				.getBean("defaultSessionManager"));
		IMSession receiverSession = sessionManager.getSession(data.getReceiver());
		if(receiverSession != null){
			receiverSession.write(data);
		}else{
			//该用户不存在或没在线
		}
	}
	
	/**保存消息*/
	private void saveMessage(Data data){
		
	}

	@Override
	public int getCmd() {
		return Cmd.CHAT_MSG_VALUE;
	}

}
