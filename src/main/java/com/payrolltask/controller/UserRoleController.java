package com.payrolltask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.entity.UserRoleEntity;
import com.payrolltask.payload.UserRoleRequest;
import com.payrolltask.serviceInterface.UserRoleServiceInterface;

@RestController
@RequestMapping("/userrole")
public class UserRoleController 
{
	@Autowired
	 private UserRoleServiceInterface userRoleServiceInterface;
	
	// add data
	@PostMapping
	public ResponseEntity<?>add(@RequestBody UserRoleRequest userrolerequest )
	{
	 try
	  {
		userRoleServiceInterface.add(userrolerequest);
		return new ResponseEntity<>(new SucessResponseDto("Successfully  Add useid and role id","SUCEESS","data added"),HttpStatus.OK);
	  }catch (Exception e)
	  {
		return new ResponseEntity<>("Invalid userid or roleid",HttpStatus.BAD_REQUEST);
	  }
	}
	
	
	// get all data role id and role id 
	@GetMapping
	public List<UserRoleEntity> getAll()
	{
	  return this.userRoleServiceInterface.getAll();
	}
	
	
	 // update role id 
	 @PutMapping("/update")
	 public ResponseEntity<?>updateuserrole( @RequestBody UserRoleRequest  userrolerequest)
	 {
	  try
		{
	      userRoleServiceInterface.updateuserrole(userrolerequest);
		  return new ResponseEntity<>(new SucessResponseDto("update successfully","updated","update"),HttpStatus.OK);
		}catch (Exception e)
			{
				return new ResponseEntity<>("Invalid role id or user id",HttpStatus.BAD_REQUEST);
			}
		}
	 // delete user id and role id 
	 @DeleteMapping
	 public ResponseEntity<?>deleteuserroles (@RequestBody UserRoleRequest userrolerequest)
	  {
		 try
			{
				userRoleServiceInterface.deleteuserroles(userrolerequest);
				return new ResponseEntity<> (new SucessResponseDto("delete successfully","delete","deleted successfully") ,HttpStatus.OK);
			}catch(Exception e)
				{
					return new ResponseEntity<>("Invalid userid or roleid",HttpStatus.BAD_REQUEST);
				}
			}

}
