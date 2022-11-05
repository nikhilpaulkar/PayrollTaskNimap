package com.payrolltask.serviceInterface;

import org.springframework.data.domain.Page;

import com.payrolltask.dto.UserDto;
import com.payrolltask.entity.Users;


public interface UserServiceInterface
{
   Users createuser(UserDto  userDto);
	
	Page<IUserListDto> getAllUsers(String search, String pageNumber, String pageSize);

	UserDto getUserId(Long id);
	
	UserDto updateUser(UserDto userDto,Long id);
	
	void deleteUser(Long id);
}
