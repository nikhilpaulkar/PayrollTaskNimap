package com.payrolltask.dto;

import java.util.Date;

public class LoggerDto
{
	 private long userid;
	 private String token;
	 private Date expiredAt;
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpiredAt() {
		return expiredAt;
	}
	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}
	public LoggerDto(long userid, String token, Date expiredAt) {
		super();
		this.userid = userid;
		this.token = token;
		this.expiredAt = expiredAt;
	}
	public LoggerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
