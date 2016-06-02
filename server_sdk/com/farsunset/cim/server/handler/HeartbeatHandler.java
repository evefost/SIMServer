 
package com.farsunset.cim.server.handler;

import com.farsunset.cim.server.constant.CIMConstant;
import com.farsunset.cim.server.mutual.ReplyBody;
import com.farsunset.cim.server.mutual.SentBody;
import com.farsunset.cim.server.session.IMSession;

/**
 *客户端心跳实现
 * 
 * @author
 */
public class HeartbeatHandler implements CIMRequestHandler {


	public ReplyBody process(IMSession session, SentBody message) {

		//收到心跳响应，清除发送心跳请求标记
		session.removeTag(CIMConstant.HEARTBEAT_PINGED);
		return null;
	}
	
 
	
}