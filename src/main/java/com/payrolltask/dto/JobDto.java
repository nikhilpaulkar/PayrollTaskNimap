package com.payrolltask.dto;

public class JobDto
{
	private Long id;
	private String jobtitle;
	private String location;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
	public JobDto(Long id, String jobtitle, String location) {
		super();
		this.id = id;
		this.jobtitle = jobtitle;
		this.location = location;
		
	}
	public JobDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
