package com.payrolltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payrolltask.dto.ErrorResponseDto;
import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.dto.UserJobDto;
import com.payrolltask.serviceInterface.IUserJobListDto;
import com.payrolltask.serviceInterface.UserJobServiceInterface;
import com.payrolltask.utility.GlobalFunction;

@RestController
@RequestMapping("/userjob")
public class UserJobController 
{
  @Autowired
  private UserJobServiceInterface userJobServiceInterface;
  
  @PreAuthorize("hasRole('applycandidate')")
  @PostMapping
  public ResponseEntity<?> addUserJob(@RequestBody UserJobDto userJobDto,@RequestAttribute(GlobalFunction.USER_ID) Long id)
  {
	   try
	    { 
		  userJobServiceInterface.applyjob(userJobDto,id);
		  
		  return new ResponseEntity<>(new SucessResponseDto("successfullly applied ","success","successfully applied job"),HttpStatus.ACCEPTED);
		}catch(Exception e)
		{
		  return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(),"Job id not Found"),HttpStatus.BAD_REQUEST);
		}
	}
  
    @PreAuthorize("hasRole('admingetlist')")
    @GetMapping()
	public ResponseEntity<?> getAllusers(
			@RequestParam(defaultValue = "")  String search,
			@RequestParam(defaultValue = "1") String pageNumber,
			@RequestParam(defaultValue = "5") String pageSize,
			@RequestParam(defaultValue = "")  String userid,
			@RequestParam(defaultValue = "")  String jobid)
	{
		
		Page<IUserJobListDto> entity= userJobServiceInterface.getAllcandidate(search,pageNumber,pageSize,userid,jobid);
		if(entity.getTotalElements()!=0)
		{
			return new ResponseEntity<>(new SucessResponseDto("User-JOB List","Success", entity.getContent()),HttpStatus.ACCEPTED);
		}
		else
		{
		   return new ResponseEntity<>(new ErrorResponseDto("not found","not found"),HttpStatus.NOT_FOUND);
	    }
	}
	
  
}

