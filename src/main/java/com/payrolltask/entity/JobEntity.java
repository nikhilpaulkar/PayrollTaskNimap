package com.payrolltask.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@UpdateTimestamp
    private Date updatedat;
	
	@CreationTimestamp
    private Date createdat;
	private boolean isactive=true;
	@OneToOne
	private Users recruiter;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "jobs")
	private List<UserJobEntity> userJobEntity;
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

    public List<UserJobEntity> getUserJobEntity() {
		return userJobEntity;
	}

	public void setUserJobEntity(List<UserJobEntity> userJobEntity) {
		this.userJobEntity = userJobEntity;
	}

	public Date getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}

	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	public Users getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Users recruiter) {
		this.recruiter = recruiter;
	}

	public JobEntity(Long id, String jobtitle, String location, Date updatedat, Date createdat, boolean isactive,
			Users recruiter, List<UserJobEntity> userJobEntity) {
		super();
		this.id = id;
		this.jobtitle = jobtitle;
		this.location = location;
		this.updatedat = updatedat;
		this.createdat = createdat;
		this.isactive = isactive;
		this.recruiter = recruiter;
		this.userJobEntity = userJobEntity;
	}

	public JobEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
