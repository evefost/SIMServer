
package com.im.manage.session;

import java.util.Collection;

import com.im.sdk.protocol.Message.Data;
import com.im.server.core.IMSession;

import io.netty.channel.ChannelHandlerContext;

/*
* 集群 session管理实现示例， 各位可以自行实现 AbstractSessionManager接口来实现自己的 session管理
*服务器集群时 须要将CIMSession 信息存入数据库或者nosql 等 第三方存储空间中，便于所有服务器都可以访问
*/
public class ClusterSessionManager implements SessionManager {

	// private static HashMap<String,IoSession> sessions =new
	// HashMap<String,IoSession>();

	/**
	 *  
	 */
	public void addSession(String account, IMSession session) {

		/**
		 * 下面 将session 存入数据库
		 */

	}

	public IMSession getSession(String account) {

		// 这里查询数据库
		/*
		 * CIMSession session = database.getSession(account);
		 * session.setIoSession(((NioSocketAcceptor)
		 * ContextHolder.getBean("serverAcceptor")).getManagedSessions().get(
		 * session.getNid())); return session;
		 */
		return null;
	}

	public Collection<IMSession> getSessions() {
		/*
		 * //这里查询数据库 return database.getSessions();
		 */
		return null;
	}

	public void removeSession(IMSession session) {

		// database.removeSession(session.getAttribute(CIMConstant.SESSION_KEY));*/
	}

	public void removeSession(String account) {

		// database.removeSession(account);*/

	}

	public boolean containsSession(IMSession ios) {
		// return database.containsCIMSession(session.getAccount());

		return false;
	}

	public String getAccount(IMSession ios) {
		return null;
	}

	@Override
	public void addSession(IMSession session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAreadyLogin(Data data){
		// TODO Auto-generated method stub
		return false;
	}

}
