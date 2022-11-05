package com.payrolltask.dto;

public class RolePermissionDto {
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


    
	public RolePermissionDto(Long roleid, Long permissionid) {
		super();
		this.roleid = roleid;
		this.permissionid = permissionid;
	}



	public RolePermissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
   
	
}
