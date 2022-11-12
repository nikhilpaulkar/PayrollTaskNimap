package com.payrolltask.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="excel")
public class ExcelEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	private String email;
	private String name;
	private String jobtitle;
	private String location;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public ExcelEntity(Long id, String email, String name, String jobtitle, String location) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.jobtitle = jobtitle;
		this.location = location;
	}
	public ExcelEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
