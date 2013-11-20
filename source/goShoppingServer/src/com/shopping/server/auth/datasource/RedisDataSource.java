package com.shopping.server.auth.datasource;

import org.apache.commons.pool.impl.GenericObjectPool.Config;

public class RedisDataSource{

	private String ip;
	private int port;
	private String password;
	private int conOuttime;
	private int database;
	
	private Config config;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getConOuttime() {
		return conOuttime;
	}

	public void setConOuttime(int conOuttime) {
		this.conOuttime = conOuttime;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}


	@Override
	public String toString() {
		return "RedisDataSource [ip=" + ip + ", port=" + port + ", password="
				+ password + ", conOuttime=" + conOuttime + ", database="
				+ database + ", config=" + config + "]";
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
}
