package com.payrolltask.dto;

public class RoleDto 
{
	private Long id;
	private String roleName;
	
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

   public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
  


	public RoleDto(Long id, String roleName) {
	super();
	this.id = id;
	this.roleName = roleName;
}

	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
	
  
	 


}
