package com.im.server.util;

import com.im.sdk.protocol.Message;
import com.im.server.core.IMSession;

public class SessionUtils {
	
	public static void reply(IMSession newSession,Integer cmd){
		Message.Data.Builder reply = Message.Data.newBuilder();
		reply.setCmd(cmd);
		reply.setCreateTime(System.currentTimeMillis());
		newSession.write(reply);
	}

}
