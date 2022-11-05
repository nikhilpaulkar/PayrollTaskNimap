package com.payrolltask.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Table(name="role_permission")
@Where(clause = "is_active=true")
@SQLDelete(sql="UPDATE role_permission  SET is_active=false WHERE permission_id=? AND role_id=?")
@AssociationOverrides({@AssociationOverride(name="pk.roles",joinColumns = @JoinColumn(name="role_id")),@AssociationOverride(name="pk.permission",joinColumns = @JoinColumn(name="permission_id"))})
@Entity
public class RolePermissionEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@EmbeddedId
	private RolePermissionId pk = new RolePermissionId();
	
	
	@CreationTimestamp
	private Date createdAt;
	
	@UpdateTimestamp
	private Date updatedAt;
	
	private boolean isActive=true;

	public RolePermissionId getPk() {
		return pk;
	}

	public void setPk(RolePermissionId pk) {
		this.pk = pk;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public RolePermissionEntity(RolePermissionId pk, Date createdAt, Date updatedAt, boolean isActive) {
		super();
		this.pk = pk;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isActive = isActive;
	}

	public RolePermissionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
