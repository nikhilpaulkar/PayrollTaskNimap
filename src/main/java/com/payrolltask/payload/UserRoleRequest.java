package com.payrolltask.payload;

public class UserRoleRequest
{
	private Long userid;
	private Long roleid;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Long getRoleid() {
		return roleid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public UserRoleRequest(Long userid, Long roleid) {
		super();
		this.userid = userid;
		this.roleid = roleid;
	}
	public UserRoleRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
