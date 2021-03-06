package com.im.server.core;

import java.io.Serializable;
import java.util.UUID;

import com.im.sdk.protocol.Message.Data;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class IMSession implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Channel channel;
	private String clientId;
	private String clientName;
	private String clientVersion;
	private long bindTime;
	private String uid;
	private long loginTime;
	private String encriptKey;
	
	public static IMSession buildSesion(ChannelHandlerContext ctx,Data data){
		IMSession session = new IMSession(ctx.channel());
		session.setClientId(data.getClientId());
		session.setClientName(data.getClientName());
		session.setClientVersion(data.getClientVersion());
		session.setBindTime(System.currentTimeMillis());
		session.setEncriptKey(UUID.randomUUID().toString().substring(0, 6));
		
		return session;
	}

	public String getEncriptKey() {
		return encriptKey;
	}

	public void setEncriptKey(String encriptKey) {
		this.encriptKey = encriptKey;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	private IMSession(Channel channel){
		this.channel = channel;
	}
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}



	public boolean write(Object msg) {
		if (channel != null && channel.isActive()) {
			return channel.writeAndFlush(msg).awaitUninterruptibly(5000);
		}
		return false;
	}
	
	public void close(boolean immediately) {
		if (channel != null) {
			channel.disconnect();
			channel.close();
		}
	}
	


	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public long getBindTime() {
		return bindTime;
	}

	public void setBindTime(long bindTime) {
		this.bindTime = bindTime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}