package com.payrolltask.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="jobentity")
@Where(clause="isactive=true")
@SQLDelete(sql="UPDATE jobentity SET isactive=false WHERE id=?")
public class JobEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String jobtitle;
	private String location;
	private boolean isactive=true;

	@OneToOne
	private RoleEntity recruiter;

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

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	
	public RoleEntity getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(RoleEntity recruiter) {
		this.recruiter = recruiter;
	}



	public JobEntity(Long id, String jobtitle, String location, boolean isactive, RoleEntity recruiter) {
		super();
		this.id = id;
		this.jobtitle = jobtitle;
		this.location = location;
		this.isactive = isactive;
		this.recruiter = recruiter;
	}

	public JobEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
