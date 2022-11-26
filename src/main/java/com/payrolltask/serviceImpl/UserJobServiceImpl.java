package com.payrolltask.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.UserJobDto;
import com.payrolltask.entity.JobEntity;
import com.payrolltask.entity.UserJobEntity;
import com.payrolltask.entity.Users;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.JobRepository;
import com.payrolltask.repository.UserJobRepository;
import com.payrolltask.repository.UserRepository;
import com.payrolltask.serviceInterface.IUserJobListDto;
import com.payrolltask.serviceInterface.UserJobServiceInterface;
import com.payrolltask.utility.Pagination;

@Service
public class UserJobServiceImpl implements UserJobServiceInterface 
{
  @Autowired
  private UserJobRepository userJobRepository;
   
  @Autowired
  private EmailServiceImpl emailServiceImpl;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private JobRepository jobRepository;
 
  
 
  @Override
  public void applyjob(UserJobDto userJobDto,Long id)
  {
	  
	  Users user=userRepository.findById(id).orElseThrow();
	  
	  ArrayList<JobEntity> jobEntity = new ArrayList<>();
	  for(int i=0;i<userJobDto.getJobId().size();i++)
	  {
		 Long jobId=userJobDto.getJobId().get(i);
		 JobEntity job=jobRepository.findById(jobId).orElseThrow(()->
		 new ResourceNotFoundException("Not Found Job Id"));
		
	     UserJobEntity userJob=new UserJobEntity();
	     userJob.setJobs(job);
	     userJob.setUser(user);
	     emailServiceImpl.mail(user.getEmail(), "apply  job  successfully",job.getJobtitle());
	  
	     String email= job.getRecruiter().getEmail();
	     emailServiceImpl.mail(email, "apply  job  successfully",job.getJobtitle());

	     userJobRepository.save(userJob);
	  }				
  }

  @Override
  public Page<IUserJobListDto> getAllcandidate(String search, String pageNumber, String pageSize,String userid,String jobid)
  {
		Pageable pagable=new Pagination().getPagination(pageNumber, pageSize);
		
	    return userJobRepository.findListUserJob(userid,jobid,pagable,IUserJobListDto.class);
		
  }

 

}
