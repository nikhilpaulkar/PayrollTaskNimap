package com.payrolltask.serviceInterface;

import java.util.List;

import com.payrolltask.entity.RolePermissionEntity;
import com.payrolltask.payload.RolePermissionRequest;

public interface RolePermissionServiceInterface 
{
	void add(RolePermissionRequest rolePermissionRequest);
	List<RolePermissionEntity> getAll();
	void updateRolePermission(RolePermissionRequest rolePermissionRequest);
	void deleteRolePermission(RolePermissionRequest rolePermissionRequest);

}
