package com.payrolltask.serviceInterface;

import java.util.List;

import com.payrolltask.entity.UserRoleEntity;
import com.payrolltask.payload.UserRoleRequest;


public interface UserRoleServiceInterface
{
	void add(UserRoleRequest userRoleRequest);
	List<UserRoleEntity> getAll();
	void updateuserrole (UserRoleRequest userrolerequest);
	UserRoleEntity deleteuserroles(UserRoleRequest userrolerequest);
}
