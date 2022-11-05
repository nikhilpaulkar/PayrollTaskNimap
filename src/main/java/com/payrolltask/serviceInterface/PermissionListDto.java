package com.payrolltask.serviceInterface;

public interface PermissionListDto 
{
	public Long getId();
	public String getActionName();
	public String getMethod();
	public String getBaseUrl();
	public String getPath();
}
