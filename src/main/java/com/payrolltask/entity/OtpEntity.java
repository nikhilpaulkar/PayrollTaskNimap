package com.payrolltask.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name="otpentity")
public class OtpEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer otp;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Users user;
	@CreationTimestamp
	private Date createdat;
	@UpdateTimestamp
	private Date updatedat;
	private String email;
	private Date expiredat;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getOtp() {
		return otp;
	}
	public void setOtp(Integer otp) {
		this.otp = otp;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Date getCreatedat() {
		return createdat;
	}
	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}
	public Date getUpdatedat() {
		return updatedat;
	}
	public void setUpdatedat(Date updatedat) {
		this.updatedat = updatedat;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getExpiredat() {
		return expiredat;
	}
	public void setExpiredat(Date expiredat) {
		this.expiredat = expiredat;
	}
	public OtpEntity(Long id, Integer otp, Users user, Date createdat, Date updatedat, String email, Date expiredat) {
		super();
		this.id = id;
		this.otp = otp;
		this.user = user;
		this.createdat = createdat;
		this.updatedat = updatedat;
		this.email = email;
		this.expiredat = expiredat;
	}
	public OtpEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
