package com.payrolltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payrolltask.dto.ErrorResponseDto;
import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.dto.UserDto;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.serviceInterface.IUserListDto;
import com.payrolltask.serviceInterface.UserServiceInterface;

@RestController
@RequestMapping("/user")
public class UserController
{
  @Autowired
  private UserServiceInterface  userServiceInterface;
  
    //get all users With Pagination
	@GetMapping()
	public ResponseEntity<?> getAllusers(
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNumber,
			@RequestParam(defaultValue = "5") String pageSize)
	{
		
		Page<IUserListDto> entity= userServiceInterface.getAllUsers(search,pageNumber,pageSize);
		if(entity.getTotalElements()!=0)
		{
			return new ResponseEntity<>(entity.getContent(), HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
	    }
	}
  
	// get user by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById( @PathVariable Long id)
	{
		 try 
			{
	            UserDto userDto=this.userServiceInterface.getUserId(id);
				return new ResponseEntity<>(new SucessResponseDto("Success","Success", userDto),HttpStatus.OK);
			}catch(ResourceNotFoundException e) 
			{
				return new ResponseEntity<>( new ErrorResponseDto("not found","User Not Found"),HttpStatus.NOT_FOUND);
			}
	}
  
	
	
	//update User by id
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDataByUserId(@RequestBody UserDto userDto,@PathVariable Long id)
	{
	  try
		{
			this.userServiceInterface.updateUser(userDto,id);
			 return new ResponseEntity<>(new SucessResponseDto("update", "update sucessfully", "updated"),HttpStatus.OK);
		
		}catch(Exception e)
		{
			return new ResponseEntity<>(new ErrorResponseDto("Please Enter Valid UserId..", "Not Updated Data.."),HttpStatus.NOT_FOUND);
		}
				
		}

	// delete by id 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id)
	{

	  try 
		{
			this.userServiceInterface.deleteUser(id);
			return new  ResponseEntity<>(new SucessResponseDto("Success","Success", "Deleted Successfully!"),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{
         return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"User Not Found"),HttpStatus.NOT_FOUND);
		}
		
	}

}
