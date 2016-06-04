package com.im.server.handler;

import com.farsunset.cim.server.constant.CIMConstant;
import com.im.sdk.protocal.Message;
import com.im.sdk.protocal.Message.Data;
import com.im.sdk.protocal.Message.Data.Cmd;
import com.im.server.core.IMSession;
import com.xie.im.session.manager.ContextHolder;
import com.xie.im.session.manager.SessionManager;

import io.netty.channel.ChannelHandlerContext;

/***
 * 登出处理
 * @author mis
 *
 */
public class LogoutHandler implements IRequestHandler {

	@Override
	public void hand(ChannelHandlerContext ctx, Data data) {
	
		try {
			SessionManager sessionManager  =  ((SessionManager) ContextHolder.getBean("defaultSessionManager"));
			IMSession ios = sessionManager.getSession(data.getSender());
			String account =ios.getTag(CIMConstant.SESSION_KEY).toString();
			ios.removeTag(CIMConstant.SESSION_KEY);
			ios.close(true);

			sessionManager.removeSession(account);
			 
			Message.Data.Builder reply = Message.Data.newBuilder();
			reply.setCmd(Message.Data.Cmd.LOGOUT_VALUE);
			reply.setCreateTime(data.getCreateTime());
			reply.setSender(data.getSender());
			reply.setLoginSuccess(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int getCmd() {
		return Cmd.LOGOUT_VALUE;
	}

}
