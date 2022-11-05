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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Where(clause = "is_active=true")
@SQLDelete(sql="UPDATE permission SET is_active=false WHERE id=?")
@Table(name="permission")
public class PermissionEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String actionName;
	
	private String method;
	
	private String baseUrl;
	private String path;
	
	@CreationTimestamp
	private Date createdAt;
	
	@UpdateTimestamp
	private Date updatedAt;
    private boolean isActive=true;
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "pk.permission",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<RolePermissionEntity> rolePermissionEntity;
    
    
	public List<RolePermissionEntity> getRolePermissionEntity() {
		return rolePermissionEntity;
	}
	public void setRolePermissionEntity(List<RolePermissionEntity> rolePermissionEntity) {
		this.rolePermissionEntity = rolePermissionEntity;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
	
	
	public PermissionEntity(Long id, String actionName, String method, String baseUrl, String path, Date createdAt,
			Date updatedAt, boolean isActive, List<RolePermissionEntity> rolePermissionEntity) {
		super();
		this.id = id;
		this.actionName = actionName;
		this.method = method;
		this.baseUrl = baseUrl;
		this.path = path;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isActive = isActive;
		this.rolePermissionEntity = rolePermissionEntity;
	}
	public PermissionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
