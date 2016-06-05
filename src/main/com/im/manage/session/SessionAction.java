package com.im.manage.session;

import org.apache.struts2.ServletActionContext;

import com.im.manage.session.ContextHolder;
import com.im.manage.session.SessionManager;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 后台session管理
 * @author xie
 *
 */
public class SessionAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String list() {
		ServletActionContext.getRequest().setAttribute("sessionList",
				((SessionManager) ContextHolder.getBean("defaultSessionManager")).getSessions());

		return "list";
	}

}
