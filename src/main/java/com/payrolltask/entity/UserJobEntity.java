package com.payrolltask.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="userjob")
@Where(clause="isactive=true")
@SQLDelete(sql="UPDATE userjob SET isactive=false WHERE id=?")
public class UserJobEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
    private Users user;

	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
    private JobEntity jobs;
	
	private boolean isactive=true;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public JobEntity getJobs() {
		return jobs;
	}


	public void setJobs(JobEntity jobs) {
		this.jobs = jobs;
	}

    
	public boolean isIsactive() {
		return isactive;
	}


	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}


	

	public UserJobEntity(Long id, Users user, JobEntity jobs, boolean isactive) {
		super();
		this.id = id;
		this.user = user;
		this.jobs = jobs;
		this.isactive = isactive;
	}


	public UserJobEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
