package com.payrolltask.serviceInterface;


import org.springframework.data.domain.Page;

import com.payrolltask.entity.UserRoleEntity;
import com.payrolltask.payload.UserRoleRequest;


public interface UserRoleServiceInterface
{
	void add(UserRoleRequest userRoleRequest);
	Page<IUserRoleListDto> getAll(String search, String pageNumber, String pageSize);
	void updateuserrole (UserRoleRequest userrolerequest);
	UserRoleEntity deleteuserroles(UserRoleRequest userrolerequest);
	
	
}
