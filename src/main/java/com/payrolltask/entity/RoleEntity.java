package com.payrolltask.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name="role")
@Where(clause="isactive=true")
@SQLDelete(sql="UPDATE role SET isactive=false WHERE id=?")
@Component
public class RoleEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roleName;
	private boolean isactive=true;
	
	
	@UpdateTimestamp
    private Date updatedat;
	
	@CreationTimestamp
    private Date createdat;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="task.role",cascade=CascadeType.ALL)
	@JsonBackReference
	private List<UserRoleEntity> userrole;
	

	public List<UserRoleEntity> getUserrole() {
		return userrole;
	}

	public void setUserrole(List<UserRoleEntity> userrole) {
		this.userrole = userrole;
	}

	

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

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
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

	

	public RoleEntity(Long id, String roleName, boolean isactive, Date updatedat, Date createdat,
			List<UserRoleEntity> userrole) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.isactive = isactive;
		this.updatedat = updatedat;
		this.createdat = createdat;
		this.userrole = userrole;
	}

	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


}

