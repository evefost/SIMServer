package com.im.protocol.handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

import com.im.constant.CIMConstant;
import com.im.manage.session.ContextHolder;
import com.im.manage.session.SessionManager;
import com.im.sdk.protocol.Message;
import com.im.sdk.protocol.Message.Data;
import com.im.sdk.protocol.Message.Data.Cmd;
import com.im.server.core.IMSession;
import com.im.server.core.IMSession.User;
import com.im.server.core.ProtocolHandler;
import com.im.server.util.SessionUtils;

import io.netty.channel.ChannelHandlerContext;

public class LoginHandler extends ProtocolHandler {

	@Override
	public void handleRequest(ChannelHandlerContext ctx, Data data) {
		System.out.println("LoginHandler=================>>");
		// 创建新的用户信息
		User user = IMSession.buildUser();
		user.setUid(data.getSender());
		user.setLoginTime(System.currentTimeMillis());
		IMSession newSession = getSessionManager().getSession(data.getClientId());
		newSession.setUser(user);
		
		// 由于客户端断线服务端可能会无法获知的情况，客户端重连时，需要关闭旧的连接
		IMSession oldSession = getSessionManager().getSession(data.getSender());
		if (oldSession != null && !oldSession.equals(newSession)) {
			System.out.println("LoginHandler 则让另一个终端下线:" + data.getSender());
			//oldSession.removeTag(CIMConstant.SESSION_KEY);
			Message.Data.Builder offLineReply = Message.Data.newBuilder();
			// 强行下线消息类型
			offLineReply.setCmd(Cmd.OTHER_LOGGIN_VALUE);
			offLineReply.setCreateTime(data.getCreateTime());
			offLineReply.setReceiver(data.getSender());
//				if (!oldSession.isLocalhost()) {
//					/*
//					 * 判断当前session是否连接于本台服务器，如不是发往目标服务器处理
//					 * MessageDispatcher.execute(msg, oldSession.getHost());
//					 */
//				} else {
//					oldSession.write(offLineReply);
//					oldSession.close(true);
//					getSessionManager().removeSession(oldSession);
//					oldSession = null;
//				}
			oldSession = null;

		} else {
			// 新登录
		
		}
		System.out.println("LoginHandler 登录成功,回应客户端:" + data.getSender());
		getSessionManager().addSession(newSession);
		SessionUtils.reply(newSession, Message.Data.Cmd.LOGIN_ECHO_VALUE);
		checkAndSendOffLineMessages(newSession);

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
