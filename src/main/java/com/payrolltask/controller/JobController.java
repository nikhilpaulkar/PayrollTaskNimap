package com.payrolltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.payrolltask.dto.JobDto;
import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.serviceInterface.IJobListDto;
import com.payrolltask.serviceInterface.JobServiceInterface;

@RequestMapping("/job")
@RestController
public class JobController
{
  @Autowired
  private JobServiceInterface jobServiceInterface;
  
  @PostMapping()
	public ResponseEntity<?>applyjob(@RequestBody JobDto jobDto)
	{
		try
		{
			jobServiceInterface.addjob(jobDto);
			return new ResponseEntity<>(new SucessResponseDto("add job","successfully", "success"),HttpStatus.CREATED);
			
		}catch (Exception e)
		{
			  return new ResponseEntity<>(new ErrorResponseDto( "Already add  ","already add"),HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping()
	public ResponseEntity<?> getAlljob(
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNumber,
			@RequestParam(defaultValue = "5") String pageSize)
	 {
		
		Page<IJobListDto> entity= jobServiceInterface.getAllJob(search,pageNumber,pageSize);
		if(entity.getTotalElements()!=0)
		{
			return new ResponseEntity<>(entity.getContent(), HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<>("fail",HttpStatus.BAD_REQUEST);
	    }
	 }

	
	@GetMapping("/{id}")
	public ResponseEntity<?>getjobid(@PathVariable Long id)
	{
		try 
		{
          JobDto jobDto=this.jobServiceInterface.getjobById(id);
          return new ResponseEntity<>(new SucessResponseDto("Success","Success", jobDto),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{
			return new ResponseEntity<>( new ErrorResponseDto("not found","job Not Found"),HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>updateByJobId(@RequestBody JobDto jobDto,@PathVariable Long id)
	{
		try
		{
			
		  this.jobServiceInterface.updatejob(jobDto, id);
		  return new ResponseEntity<>(new SucessResponseDto("update", "update sucessfully", "updated"),HttpStatus.OK);
	
		}catch(Exception e)
		{
			return new ResponseEntity<>(new ErrorResponseDto("Please Enter Valid JobId..", "Not Updated Data.."),HttpStatus.NOT_FOUND);
		}
			
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletejob(@PathVariable Long id)
	{

		try 
		{
			this.jobServiceInterface.deletejob(id);
			return new  ResponseEntity<>(new SucessResponseDto("Success","Success", "Deleted Successfully!"),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{

			return new ResponseEntity<>( new ErrorResponseDto("not found","job Not Found"),HttpStatus.NOT_FOUND);
	    }
	
}
}
