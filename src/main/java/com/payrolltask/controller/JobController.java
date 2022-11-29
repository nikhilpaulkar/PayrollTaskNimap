package com.payrolltask.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payrolltask.dto.ErrorResponseDto;
import com.payrolltask.dto.JobDto;
import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.serviceInterface.IJobListDto;
import com.payrolltask.serviceInterface.IListRecruiterDto;
import com.payrolltask.serviceInterface.IRecruiterDto;
import com.payrolltask.serviceInterface.JobServiceInterface;
import com.payrolltask.utility.ApiUrls;
import com.payrolltask.utility.GlobalFunction;

@RequestMapping(ApiUrls.JOB)
@RestController
public class JobController
{
  @Autowired
  private JobServiceInterface jobServiceInterface;
  
  @PreAuthorize("hasRole('applyrecruiter')")
  @PostMapping()
  public ResponseEntity<?>applyjob(@RequestBody JobDto jobDto,@RequestAttribute(GlobalFunction.USER_ID) Long id)
	{
	  try
		{
		jobServiceInterface.addjob(jobDto,id);
		return new ResponseEntity<>(new SucessResponseDto("add job","successfully", "success"),HttpStatus.CREATED);
	   }catch (Exception e)
	  {
		  return new ResponseEntity<>(new ErrorResponseDto( e.getMessage(),"already add"),HttpStatus.BAD_REQUEST);

	  }
	}
  
    @PreAuthorize("hasRole('JobView')")
	@GetMapping(ApiUrls.GET_ALL)
	public ResponseEntity<?> getAlljob(
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNumber,
			@RequestParam(defaultValue = "5") String pageSize)
	 {
		
		Page<IJobListDto> entity= jobServiceInterface.getAllJob(search,pageNumber,pageSize);
		if(entity.getTotalElements()!=0)
		{
			return new ResponseEntity<>(new SucessResponseDto("success","successfully", entity.getContent()),HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<>( new ErrorResponseDto("not found"," Not Found"),HttpStatus.NOT_FOUND);
	    }
	 }

    @PreAuthorize("hasRole('JobView')")
	@GetMapping("/{id}")
	public ResponseEntity<?>getjobid(@PathVariable Long id)
	{
		try 
		{
          List<IJobListDto> jobDto=this.jobServiceInterface.getjobById(id);
          return new ResponseEntity<>(new SucessResponseDto("Success","Success",jobDto),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{
			return new ResponseEntity<>( new ErrorResponseDto("not found","job Not Found"),HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PreAuthorize("hasRole('JobUpdate')")
	@PutMapping("/{id}")
	public ResponseEntity<?>updateByJobId(@RequestBody JobDto jobDto,@PathVariable Long id)
	{
		try
		{
			
		  this.jobServiceInterface.updatejob(jobDto, id);
		  return new ResponseEntity<>(new SucessResponseDto("update", "update sucessfully", "updated"),HttpStatus.OK);
	
		}catch(Exception e)
		{
			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(),"not found"),HttpStatus.NOT_FOUND);
		}
			
	}
	
	
	@PreAuthorize("hasRole('admingetlist')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletejob(@PathVariable Long id)
	{

		try 
		{
			this.jobServiceInterface.deletejob(id);
			return new  ResponseEntity<>(new SucessResponseDto("Success","Success", "Deleted Successfully!"),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{

			return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"not found"),HttpStatus.NOT_FOUND);
	    }
	
    }
	
	
	
	// API for Get Job List By Recruiter id
	@PreAuthorize("hasRole('applyrecruiter')")
	@GetMapping("/getjobrecruiter/{id}")
	public ResponseEntity<?> getjobbyrecruiter(@RequestAttribute(GlobalFunction.USER_ID) Long id)
	{
		try 
		{
		  List<IRecruiterDto> job=this.jobServiceInterface.getJobbyRecruiter(id);
			return new  ResponseEntity<>(new SucessResponseDto(" get job List","Success", job),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{

			return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"not found"),HttpStatus.NOT_FOUND);
	    }
	}
	
	@PreAuthorize("hasRole('applyrecruiter')")
	@GetMapping("/recruiter")
	public ResponseEntity<?> getjobbyrecruite(@RequestAttribute(GlobalFunction.USER_ID) Long recruiter_id)
	{
		try 
		{
			
		  List<IListRecruiterDto> job=this.jobServiceInterface.getRecruiterJobsById(recruiter_id);
			return new  ResponseEntity<>(new SucessResponseDto(" get job List","Success", job),HttpStatus.OK);
		}catch(ResourceNotFoundException e) 
		{

			return new ResponseEntity<>( new ErrorResponseDto(e.getMessage(),"not found"),HttpStatus.NOT_FOUND);
	    }
	}
	
	
}
