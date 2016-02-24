package com.im.server.handler;

import com.farsunset.cim.server.constant.CIMConstant;
import com.farsunset.cim.server.session.CIMSession;
import com.farsunset.cim.server.session.DefaultSessionManager;
import com.farsunset.cim.util.ContextHolder;
import com.im.sdk.protocal.Message;
import com.im.sdk.protocal.Message.Data;
import com.im.sdk.protocal.Message.Data.Cmd;

import io.netty.channel.ChannelHandlerContext;

/***
 * 登出处理
 * @author mis
 *
 */
public class LogoutHandler implements IRequestHandler {

	@Override
	public void hand(ChannelHandlerContext ctx, Data data) {
	
		DefaultSessionManager sessionManager  =  ((DefaultSessionManager) ContextHolder.getBean("defaultSessionManager"));
		CIMSession ios = sessionManager.getSession(data.getSender());
		String account =ios.getTag(CIMConstant.SESSION_KEY).toString();
		ios.removeTag(CIMConstant.SESSION_KEY);
		ios.close(true);
	
		sessionManager.removeSession(account);
		 
		Message.Data.Builder reply = Message.Data.newBuilder();
		reply.setCmd(Message.Data.Cmd.LOGOUT_VALUE);
		reply.setCreateTime(data.getCreateTime());
		reply.setSender(data.getSender());
		reply.setLoginSuccess(true);

	}

	@Override
	public int getCmd() {
		return Cmd.LOGOUT_VALUE;
	}

}
