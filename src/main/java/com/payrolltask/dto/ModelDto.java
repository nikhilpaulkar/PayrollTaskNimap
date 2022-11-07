package com.payrolltask.dto;

public class ModelDto 
{
	private  String email;
    private	Integer otp;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getOtp() {
		return otp;
	}
	public void setOtp(Integer otp) {
		this.otp = otp;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ModelDto(String email, Integer otp, String password) {
		super();
		this.email = email;
		this.otp = otp;
		this.password = password;
	}
	public ModelDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
