package com.im.server.core;

import java.io.Serializable;

import io.netty.channel.Channel;

public class IMSession2 implements Serializable {

	private static final long serialVersionUID = 1L;
	private Channel channel;
	private ClientInfo clientInfo;
	private User user;

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public class ClientInfo {

		private String id;
		private String name;
		private String version;
		private String ip;
		private long bindTime;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public long getBindTime() {
			return bindTime;
		}

		public void setBindTime(long bindTime) {
			this.bindTime = bindTime;
		}

	}

	public class User {
		private String uid;
		private String nickname;
		private long loginTime;

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public long getLoginTime() {
			return loginTime;
		}

		public void setLoginTime(long loginTime) {
			this.loginTime = loginTime;
		}

	}

}