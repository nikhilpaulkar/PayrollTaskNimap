package com.payrolltask.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.UserDto;
import com.payrolltask.entity.Users;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.UserRepository;
import com.payrolltask.serviceInterface.IUserListDto;
import com.payrolltask.serviceInterface.UserServiceInterface;
@Service

public class UsersServiceImpl implements UserServiceInterface
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
    // Add User
	@Override
	public Users createuser(UserDto userDto)
	{
		Users user=new Users();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		return this.userRepository.save(user);
		
	}

	public Users FindByEmail(String email)
	{
		Users user =this.userRepository.findByEmail(email);
		return  user;
		
		
	}
	@Override
	public Page<IUserListDto> getAllUsers(String search, String pageNumber, String pageSize)
	{
		Pageable pagable=new com.payrolltask.utility.Pagination().getPagination(pageNumber, pageSize);
		if((search=="")||(search==null)||(search.length()==0))
		{
			return userRepository.findByOrderById(pagable,IUserListDto.class);
		}
		else
		{
			return  userRepository.findByName(search,pagable,IUserListDto.class);
		}
		
	}

	@Override
	public UserDto getUserId(Long id)
	{
		Users userEntity=userRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("user not found with id "));
		
		UserDto userDto=new UserDto();
		userDto.setName(userEntity.getName());
		userDto.setEmail(userEntity.getEmail());
		return userDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long id)
	{
		Users userEntity=userRepository.findById(id).orElseThrow(()-> 
	      new ResourceNotFoundException("Not Found User Id"));
			
		  userEntity.setEmail(userDto.getEmail());
		  userEntity.setName(userDto.getName());
		  String password=passwordEncoder.encode(userDto.getPassword());
		  userEntity.setPassword(password);
			
		   userRepository.save(userEntity);
		   return userDto;
		
	}

	@Override
	public void deleteUser(Long id)
	{
		this.userRepository.findById(id).orElseThrow( () ->
		new ResourceNotFoundException("User Not Found With Id :"+id));
		this.userRepository.deleteById(id);
		
		
	}

}
