package com.payrolltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payrolltask.dto.ErrorResponseDto;
import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.dto.UserJobDto;
import com.payrolltask.serviceInterface.UserJobServiceInterface;

@RestController
@RequestMapping("/userjob")
public class UserJobController 
{
  @Autowired
  private UserJobServiceInterface userJobServiceInterface;
  
  @PostMapping
  public ResponseEntity<?> addUserJob(@RequestBody UserJobDto userJobDto)
  {
	  try
	  {
		  //userJobServiceInterface.adduserjob(userJobDto);
		  return new ResponseEntity<>(new SucessResponseDto("successfullly applied ","success","successfully applied job"),HttpStatus.ACCEPTED);
		}catch(Exception e)
		{
		  return new ResponseEntity<>(new ErrorResponseDto( " Already apply ","already apply"),HttpStatus.BAD_REQUEST);
		}
	}
	
}
