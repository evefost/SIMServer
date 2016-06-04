package com.farsunset.cim.admin.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.im.sdk.protocal.Message;
import com.opensymphony.xwork2.ActionSupport;
import com.xie.im.session.manager.ContextHolder;
import com.xie.im.session.manager.DefaultSessionManager;

public class SessionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String list() {
		ServletActionContext.getRequest().setAttribute("sessionList",
				((SessionManager) ContextHolder.getBean("defaultSessionManager")).getSessions());

		return "list";
	}

	public void offline() throws IOException {

		String account = ServletActionContext.getRequest().getParameter("account");
		Message msg = new Message();
		msg.setType("999");// 强行下线消息类型
		msg.setReceiver(account);

		// 向客户端 发送消息
		ContextHolder.getBean(SystemMessagePusher.class).pushMessageToUser(msg);

	}
}
