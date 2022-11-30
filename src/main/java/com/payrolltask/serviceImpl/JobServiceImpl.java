package com.payrolltask.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.JobDto;
import com.payrolltask.entity.JobEntity;
import com.payrolltask.entity.Users;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.JobRepository;
import com.payrolltask.repository.UserRepository;
import com.payrolltask.serviceInterface.IJobListDto;
import com.payrolltask.serviceInterface.IListRecruiterDto;
import com.payrolltask.serviceInterface.IRecruiterDto;
import com.payrolltask.serviceInterface.JobServiceInterface;
import com.payrolltask.utility.Pagination;

@Service
public class JobServiceImpl implements JobServiceInterface
{
  @Autowired
  private JobRepository jobRepository;
  
  
  @Autowired
  private UserRepository userRepository;
  

 
  @Override
  public void addjob(JobDto jobDto,Long id) 
  {
	
	  Users user=userRepository.findById(id).orElseThrow(()->
	  new ResourceNotFoundException("Not found User"));
	  
	    
	   JobEntity jobEntity=new JobEntity();
	   jobEntity.setJobtitle(jobDto.getJobtitle());
		
	   jobEntity.setLocation(jobDto.getLocation());
		
	   jobEntity.setRecruiter(user);
		
       jobRepository.save(jobEntity);
       
       
  }

  @Override
  public Page<IJobListDto> getAllJob(String search, String pageNumber, String pageSize) 
  {
	  Pageable pagable=new Pagination().getPagination(pageNumber, pageSize);
	  
		if((search=="")||(search==null)||(search.length()==0))
		{
			return jobRepository.findByOrderByIdDesc(pagable,IJobListDto.class);
		}
		else
		{
			return  jobRepository.findByjobtitle(search,pagable,IJobListDto.class);
		}
		
	
  }

  @Override
  public List<IJobListDto> getjobById(Long id)
  {
	   JobEntity jobEntity=jobRepository.findById(id).orElseThrow(()->
	   new ResourceNotFoundException("job id not found"));
	  
	  
		List<IJobListDto> jobDtos=jobRepository.findById(id,IJobListDto.class);
		return jobDtos ;
	
      
  }

  @Override
  public JobDto updatejob(JobDto jobDto, Long id) 
  {
	  JobEntity jobEntity=jobRepository.findById(id).orElseThrow(()->
	  new ResourceNotFoundException("job id not found"));
	 
	   jobEntity.setJobtitle(jobDto.getJobtitle());
	   jobEntity.setLocation(jobDto.getLocation());
		
	   jobRepository.save(jobEntity);
	   return jobDto;
		
	  
      
  }

  @Override
  public void deletejob(Long id)
  {
	  this.jobRepository.findById(id).orElseThrow( () ->
	  new ResourceNotFoundException("job Not Found With Id :"+id));
	  this.jobRepository.deleteById(id);
	
}



  @Override
  public List<IRecruiterDto> getJobbyRecruiter(Long id)
  {
	   
	  Users user=userRepository.findById(id).orElseThrow(()->
	  new ResourceNotFoundException("User Not found"));
	  
	 List<IRecruiterDto> list= jobRepository.findgetJobbyRecruiter(user,IRecruiterDto.class);
    
     return list;
             
      
	
  }

  @Override
  public List<IListRecruiterDto> getRecruiterJobsById(Long recruiter_id)
  {
      List<IListRecruiterDto> list=jobRepository.findRecruiterByIdgetJoblist(recruiter_id,IListRecruiterDto.class);
      return list;
  }
  
  
      

	
}










