package com.payrolltask.controller;

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
import com.payrolltask.dto.PermissionDto;
import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.serviceInterface.PermissionListDto;
import com.payrolltask.serviceInterface.PermissionServiceInterface;


@RestController

@RequestMapping("/permission")
public class PermissionController
{

	@Autowired
	private PermissionServiceInterface permissionServiceInterface;
	
	// Add Permission
	@PreAuthorize("hasRole('AddPermission')")
	@PostMapping()
	public ResponseEntity<?>addPermission(@RequestBody PermissionDto permissionDto)
	{
		try
		{
			permissionServiceInterface.addPemission(permissionDto);
			return new ResponseEntity<>( new  SucessResponseDto("Add Permission", "Successfully","Successfully" ),HttpStatus.ACCEPTED);
		}catch(Exception e)
		{
			return new ResponseEntity<>( new ErrorResponseDto("not found", "does not exit"),HttpStatus.BAD_REQUEST);
		}
	}
	
	// Get All Permission With Pagination
	@PreAuthorize("hasRole('PermissionViews')")
	@GetMapping()
	public ResponseEntity<?>getAllPermission(
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNumber,
			@RequestParam(defaultValue = "5") String pageSize)
	   {

        Page<PermissionListDto> entity= permissionServiceInterface.getAllPermission(search,pageNumber,pageSize);
        
  		if(entity.getTotalElements()!=0)
  		{
  			return new ResponseEntity<>(entity.getContent(), HttpStatus.OK);
  		}
  		else
  		{
  		return new ResponseEntity<>("you entered wrong parameter",HttpStatus.BAD_REQUEST);
  	    }
	
      }
	
    // get Permission BY id 
	@PreAuthorize("hasRole('PermissionViews')")
	@GetMapping("/{id}")
	public ResponseEntity<?>getPermissionbyid(@PathVariable Long id)
	{
		try
		{
			PermissionDto entity=permissionServiceInterface.getPermissionById(id);
 			return new ResponseEntity<>(new SucessResponseDto("Success","Success", entity),HttpStatus.OK);
		}catch(Exception e)
		{
			return new ResponseEntity<>(new ErrorResponseDto("permission id is not found", "not found"),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('PermissionUpdate')")
	@PutMapping("/{id}")
	public ResponseEntity<?>updatePermission(@RequestBody PermissionDto permissionDto, @PathVariable Long id)
	{
		try
 		{
 			
 		  this.permissionServiceInterface.updatepermission(permissionDto,id);
 		  return new ResponseEntity<>(new SucessResponseDto("update", "update sucessfully", "updated"),HttpStatus.OK);
 	
 		}catch(Exception e)
 		{
 			return new ResponseEntity<>(new ErrorResponseDto("Please Enter Valid PermissionId..", "Not Updated Data.."),HttpStatus.NOT_FOUND);
 		}
 			
	}
	
	@PreAuthorize("hasRole('PermissionDelete')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deletepermissionbyid(@PathVariable Long id)
	{
		try
		{
			this.permissionServiceInterface.deletepermissionbyid(id);
			return new ResponseEntity<>(new SucessResponseDto("delete", "deleted successsfully", "deleted"),HttpStatus.ACCEPTED);
		}catch(Exception e)
		{
			return new ResponseEntity<>(new ErrorResponseDto("enter valid permermission id", "not found"),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
}
