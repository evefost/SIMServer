
package com.im.manage.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.im.constant.CIMConstant;
import com.im.server.core.IMSession;

/**
 * session管理
 * 
 * @author xie
 *
 */
public class DefaultSessionManager implements SessionManager {

	private static HashMap<String, IMSession> sessions = new HashMap<String, IMSession>();

	private static final AtomicInteger connectionsCounter = new AtomicInteger(0);

	public void addSession(IMSession session) {
		if (session != null) {
			sessions.put(session.getClientInfo().getId(), session);
			connectionsCounter.incrementAndGet();
		}
	}

	public IMSession getSession(String clientId) {

		return sessions.get(clientId);
	}

	public Collection<IMSession> getSessions() {
		return sessions.values();
	}

	public void removeSession(IMSession session) {
		sessions.remove(session.getClientInfo().getId());
	}

	public void removeSession(String account) {
		sessions.remove(account);

	}

	public boolean containsCIMSession(IMSession ios) {
		return sessions.containsKey(ios.getClientInfo().getId());
	}

	@Override
	public String getAccount(IMSession ios) {
		// TODO Auto-generated method stub
		return null;
	}

}
