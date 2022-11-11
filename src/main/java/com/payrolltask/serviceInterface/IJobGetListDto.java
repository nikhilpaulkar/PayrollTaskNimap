package com.payrolltask.serviceInterface;



public interface IJobGetListDto
{
	public Long getId() ;
	public String getJobtitle();
	public String getLocation();
	public IRoleListDto getRecruiter();
}
