package com.payrolltask.dto;

public class PermissionDto 
{
   
	private Long id;
    private String actionName;
    private String method;
	private String baseUrl;
	private String path;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	public PermissionDto(Long id, String actionName, String method, String baseUrl, String path) {
		super();
		this.id = id;
		this.actionName = actionName;
		this.method = method;
		this.baseUrl = baseUrl;
		this.path = path;
	}
	public PermissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
