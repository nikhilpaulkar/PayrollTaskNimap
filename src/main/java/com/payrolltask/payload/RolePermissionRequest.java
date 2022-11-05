package com.payrolltask.payload;

public class RolePermissionRequest 
{
	private Long roleid;
	private Long permissionid;
	
	
	
	public Long getRoleid() {
		return roleid;
	}



	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}



	public Long getPermissionid() {
		return permissionid;
	}



	public void setPermissionid(Long permissionid) {
		this.permissionid = permissionid;
	}



	public RolePermissionRequest(Long roleid, Long permissionid) {
		super();
		this.roleid = roleid;
		this.permissionid = permissionid;
	}



	public RolePermissionRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
