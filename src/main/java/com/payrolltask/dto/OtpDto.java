package com.payrolltask.dto;

import java.util.Date;

public class OtpDto 
{
	private Long id;
	private Integer otp;
	private String email;
	private Date expiredat;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getOtp() {
		return otp;
	}
	public void setOtp(Integer otp) {
		this.otp = otp;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getExpiredat() {
		return expiredat;
	}
	public void setExpiredat(Date expiredat) {
		this.expiredat = expiredat;
	}
	
	
	public OtpDto(Long id, Integer otp, String email, Date expiredat) {
		super();
		this.id = id;
		this.otp = otp;
		this.email = email;
		this.expiredat = expiredat;
	}
	public OtpDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
