package com.payrolltask.entity;

import java.io.Serializable;

import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class RolePermissionId implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@ManyToOne
	@JsonManagedReference
	private RoleEntity roles;
	
	@ManyToOne
	@JsonManagedReference
	private PermissionEntity permission;

	public RoleEntity getRoles() {
		return roles;
	}

	public void setRoles(RoleEntity roles) {
		this.roles = roles;
	}

	public PermissionEntity getPermission() {
		return permission;
	}

	public void setPermission(PermissionEntity permission) {
		this.permission = permission;
	}

	public RolePermissionId(RoleEntity roles, PermissionEntity permission) {
		super();
		this.roles = roles;
		this.permission = permission;
	}

	public RolePermissionId() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
	
}
