package com.payrolltask.dto;

public class UserRoleDto 
{
	private long roleid;
    private Long userid;
 
    
    
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	
	
	
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public UserRoleDto(long roleid, Long userid) {
		super();
		this.roleid = roleid;
		this.userid = userid;
	}
	public UserRoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
