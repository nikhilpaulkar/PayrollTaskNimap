package com.payrolltask.dto;

public class SucessResponseDto 
{
	 private String message;
	 private String messgekey;
	 private Object data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessgekey() {
		return messgekey;
	}
	public void setMessgekey(String messgekey) {
		this.messgekey = messgekey;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public SucessResponseDto(String message, String messgekey, Object data) {
		super();
		this.message = message;
		this.messgekey = messgekey;
		this.data = data;
	}
	public SucessResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
