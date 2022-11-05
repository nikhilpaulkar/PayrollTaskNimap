package com.payrolltask.errordetails;

public class Message 
{
	private String key;
	private String message;
	private Object data;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Message(String key, String message, Object data) {
		super();
		this.key = key;
		this.message = message;
		this.data = data;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
