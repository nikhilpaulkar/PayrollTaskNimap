package com.payrolltask.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payrolltask.dto.ErrorResponseDto;
import com.payrolltask.dto.RoleDto;
import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.serviceInterface.IRoleListDto;
import com.payrolltask.serviceInterface.RoleServiceInterface;



@RestController
@RequestMapping("/role")
public class RoleController 
{
	@Autowired
	private RoleServiceInterface roleServiceInterface;
	
	// add role 
	@PreAuthorize("hasRole('AddRole')")
	@PostMapping()
	public ResponseEntity<?>addrole(@RequestBody RoleDto roleDto)
	{
	  try
		{
		  roleServiceInterface.addrole(roleDto);
		  return new ResponseEntity<>(new SucessResponseDto("success","success","successfully add role"),HttpStatus.ACCEPTED);
		}catch(Exception e)
		{
		  return new ResponseEntity<>(new ErrorResponseDto( "role Already exit ","role already exxit"),HttpStatus.BAD_REQUEST);
		}
			
			
	}
	
	
	// get all roles with pagination
	@PreAuthorize("hasRole('RoleViews')")
	@GetMapping()
	public ResponseEntity<?> getAllusers(
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNumber,
			@RequestParam(defaultValue = "5") String pageSize)
	{
		
		Page<IRoleListDto> entity= roleServiceInterface.getAllrole(search,pageNumber,pageSize);
		if(entity.getTotalElements()!=0)
		{
			return new ResponseEntity<>(entity.getContent(), HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
	    }
	}
	
	// get by id 
	@PreAuthorize("hasRole('RoleViews')")
	@GetMapping("/{id}")
	public ResponseEntity<?>getRoleid(@PathVariable Long id)
	{
		try 
		{
            RoleDto roleDto=this.roleServiceInterface.getRoleId(id);
			return new ResponseEntity<>(new SucessResponseDto("Success","Success", roleDto),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{
			return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"User Not Found"),HttpStatus.NOT_FOUND);
		}
	   }
	
	// update by role id 
	@PreAuthorize("hasRole('RoleUpdate')")
	@PutMapping("/{id}")
	public ResponseEntity<?>updateByRoleId(@RequestBody RoleDto roleDto,@PathVariable Long id)
	{
		try
		{
			
		  this.roleServiceInterface.updaterole(roleDto,id);
		  return new ResponseEntity<>(new SucessResponseDto("update", "update sucessfully", "updated"),HttpStatus.OK);
	
		}catch(Exception e)
		{
			return new ResponseEntity<>(new ErrorResponseDto("Please Enter Valid RoleId..", "Not Updated Data.."),HttpStatus.NOT_FOUND);
		}
			
	}
	
	// delete by role id 
	@PreAuthorize("hasRole('RoleDelete')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleterole(@PathVariable Long id)
	{

		try 
		{
			this.roleServiceInterface.deleteRole(id);
			return new  ResponseEntity<>(new SucessResponseDto("Success","Success", "Deleted Successfully!"),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{

			return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"role Not Found"),HttpStatus.NOT_FOUND);
	    }
	}
	
//	@GetMapping()
//	public ResponseEntity<?> getAllCandidate(
//			@RequestParam(defaultValue = "") String search,
//			@RequestParam(defaultValue = "1") String pageNumber,
//			@RequestParam(defaultValue = "5") String pageSize)
//	{
//		
//		Page<ICandidateListDto> entity= roleServiceInterface.getCandidateList(search,pageNumber,pageSize);
//		if(entity.getTotalElements()!=0)
//		{
//			return new ResponseEntity<>(new SucessResponseDto("get Candidate List","success",entity.getContent()), HttpStatus.OK);
//		}
//		else
//		{
//		return new ResponseEntity<>(new ErrorResponseDto("Not found","fail"),HttpStatus.BAD_REQUEST);
//	    }
	//}
	
	
	@GetMapping("/permissions/user/{id}")
	public ResponseEntity<?> getPermissionById(@PathVariable Long id)
	{
		System.out.println("USer"+id);
		ArrayList<String>job= this.roleServiceInterface.getPermissionByUserId(id);
		
		return new ResponseEntity<>(job,HttpStatus.OK);
	}

}
