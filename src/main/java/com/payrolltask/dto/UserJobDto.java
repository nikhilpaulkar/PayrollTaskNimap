package com.payrolltask.dto;

import java.util.List;

public class UserJobDto 
{
	
	private  List<Long>jobId;
	
	public List<Long> getJobId() {
		return jobId;
	}
	public void setJobId(List<Long> jobId) {
		this.jobId = jobId;
	}
	public UserJobDto(List<Long> jobId) {
		super();
		
		this.jobId = jobId;
	}
	public UserJobDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
