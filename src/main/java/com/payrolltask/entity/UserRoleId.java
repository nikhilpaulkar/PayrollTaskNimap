package com.payrolltask.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;



@Embeddable
public class UserRoleId implements  Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
    private Users user;
	@ManyToOne
	private RoleEntity role;
	
	
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
	
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	public UserRoleId(Users user, RoleEntity role) {
		super();
		this.user = user;
		this.role = role;
	}
	public UserRoleId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
