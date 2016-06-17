
package com.im.manage.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.im.sdk.protocol.Message.Data;
import com.im.server.core.IMSession;
import com.im.server.util.StringUtils;

import io.netty.channel.ChannelHandlerContext;

/**
 * session管理
 * 
 * @author xie
 *
 */
public class DefaultSessionManager implements SessionManager {

	/**
	 * key:clientId;
	 * value:IMSession
	 */
	private static HashMap<String, IMSession> sessions = new HashMap<String, IMSession>();
	/**
	 * 用户maping
	 * key:uid;
	 * value:clientId
	 */
	private static HashMap<String, String> loginUsers = new HashMap<String, String>();

	private static final AtomicInteger connectionsCounter = new AtomicInteger(0);

	public void addSession(IMSession session) {
		if (session != null) {
			sessions.put(session.getClientInfo().getId(), session);
			if(session.getUser() != null){
				loginUsers.put(session.getUser().getUid(), session.getClientInfo().getId());
			}
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
		session.close(true);
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

	@Override
	public boolean isAreadyLogin(Data data) {
		String cid;
		String clientId = data.getClientId();
		String uid = data.getSenderId();
		String oldClientId = loginUsers.get(uid);
		//同一个uid,不同的clientId,
		if(!StringUtils.isEmpty(oldClientId) && !oldClientId.equals(clientId)){
			return true;
		}
		return false;
	}

}
