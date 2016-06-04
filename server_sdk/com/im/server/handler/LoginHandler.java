package com.im.server.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

import com.farsunset.cim.server.constant.CIMConstant;
import com.im.sdk.protocal.Message;
import com.im.sdk.protocal.Message.Data;
import com.im.sdk.protocal.Message.Data.Cmd;
import com.im.server.core.IMSession;
import com.xie.im.session.manager.ContextHolder;
import com.xie.im.session.manager.SessionManager;

import io.netty.channel.ChannelHandlerContext;

public class LoginHandler implements IRequestHandler {

	@Override
	public void hand(ChannelHandlerContext ctx, Data data) {
		System.out.println("LoginHandler=================>>");
		try {
			SessionManager sessionManager = ((SessionManager) ContextHolder
					.getBean("defaultSessionManager"));
			// 创建新的session
			IMSession newSession = new IMSession(ctx.channel());
			newSession.setAccount(data.getSender());
			newSession.setGid(UUID.randomUUID().toString());
			newSession.setHost(InetAddress.getLocalHost().getHostAddress());
			// 第一次设置心跳时间为登录时间
			newSession.setBindTime(System.currentTimeMillis());
			newSession.setHeartbeat(System.currentTimeMillis());

			// 由于客户端断线服务端可能会无法获知的情况，客户端重连时，需要关闭旧的连接
			IMSession oldSession = sessionManager.getSession(data.getSender());
			if (oldSession != null && !oldSession.equals(newSession)) {
				System.out.println("LoginHandler 则让另一个终端下线:" + data.getSender());
				oldSession.removeTag(CIMConstant.SESSION_KEY);
				Message.Data.Builder offLineReply = Message.Data.newBuilder();
				// 强行下线消息类型
				offLineReply.setCmd(Cmd.OTHER_LOGGIN_VALUE);
				offLineReply.setCreateTime(data.getCreateTime());
				offLineReply.setReceiver(data.getSender());
				if (!oldSession.isLocalhost()) {
					/*
					 * 判断当前session是否连接于本台服务器，如不是发往目标服务器处理
					 * MessageDispatcher.execute(msg, oldSession.getHost());
					 */
				} else {
					oldSession.write(offLineReply);
					oldSession.close(true);
					sessionManager.removeSession(oldSession);
					oldSession = null;
				}
				oldSession = null;

			} else {
				// 新登录
			
			}
			System.out.println("LoginHandler 登录成功,回应客户端:" + data.getSender());
			sessionManager.addSession(data.getSender(), newSession);
			Message.Data.Builder reply = Message.Data.newBuilder();
			reply.setCmd(Message.Data.Cmd.LOGIN_VALUE);
			reply.setCreateTime(data.getCreateTime());
			reply.setSender(data.getSender());
			reply.setLoginSuccess(true);
			newSession.write(reply);
			checkAndSendOffLineMessages(newSession);
		} catch (UnknownHostException e) {
			System.out.println("LoginHandler Ex:" + e.toString());
		}

	}

	/**检查并发送离线消息*/
	private void checkAndSendOffLineMessages(IMSession newSession) {
		
		List<Message.Data.Builder> mList = null;
		if(mList != null && mList.size()>0){
			
		}
		
		Message.Data.Builder data = Message.Data.newBuilder();
		data.setCmd(Cmd.CHAT_OFFLINE_MSGS_VALUE);
		data.setCreateTime(System.currentTimeMillis());
	}

	@Override
	public int getCmd() {
		return Message.Data.Cmd.LOGIN_VALUE;
	}

}