package com.payrolltask.serviceImpl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.JobDto;
import com.payrolltask.entity.JobEntity;
import com.payrolltask.entity.RoleEntity;
import com.payrolltask.entity.UserRoleEntity;
import com.payrolltask.entity.Users;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.JobRepository;
import com.payrolltask.repository.RoleRepository;
import com.payrolltask.repository.UserRepository;
import com.payrolltask.repository.UserRoleRepository;
import com.payrolltask.serviceInterface.IJobListDto;
import com.payrolltask.serviceInterface.JobServiceInterface;
import com.payrolltask.utility.Pagination;
import com.payrolltask.websecurity.JwtTokenUtil;

@Service
public class JobServiceImpl implements JobServiceInterface
{
  @Autowired
  private JobRepository jobRepository;
  
  
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private UserRoleRepository userRoleRepository;

  @Autowired
  private RoleRepository roleRepository;
  @Override
  public void addjob(JobDto jobDto,HttpServletRequest request) 
  {
	  RoleEntity role=roleRepository.findById(jobDto.getRecruiter()).orElseThrow(()->
	  new ResourceNotFoundException("enter valid role id"));
	  
	  
	  final String header=request.getHeader("Authorization");
	  String requestToken=header.substring(7);

	  final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
	   
	  Users user1=userRepository.findByEmail(email);
      Long id=user1.getId();
     
      UserRoleEntity userRoleEntity= userRoleRepository.findTaskRoleIdByTaskUserId(id);
      String name=userRoleEntity.getTask().getRole().getRoleName();
      System.out.println("Role name:"+name);
      
      
        if(name.equals("recruiter"))
        {
	     System.out.println("sjkn");
	     JobEntity jobEntity=new JobEntity();
		 jobEntity.setJobtitle(jobDto.getJobtitle());
		
		 jobEntity.setLocation(jobDto.getLocation());
		 
		  jobEntity.setRecruiter(role);
         jobRepository.save(jobEntity);
       }
      else
      {
    	  throw new ResourceNotFoundException("can not access, Only Recuiter can Add job ");
      }
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
  public JobDto getjobById(Long id)
  {
	   JobEntity jobEntity=jobRepository.findById(id).orElseThrow(()->
	   new ResourceNotFoundException("job id not found"));
	  
	  
		JobDto jobDto=new JobDto();
		jobDto.setId(jobEntity.getId());
		jobDto.setJobtitle(jobEntity.getJobtitle());
		jobDto.setLocation(jobEntity.getLocation());
		
		return jobDto;
	
      
  }

  @Override
  public JobDto updatejob(JobDto jobDto, Long id,HttpServletRequest request) 
  {
	  JobEntity jobEntity=jobRepository.findById(id).orElseThrow(()->
	  new ResourceNotFoundException("job id not found"));
	  
	  final String header=request.getHeader("Authorization");
	  String requestToken=header.substring(7);

	  final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
	   
	  Users user1=userRepository.findByEmail(email);
      Long id1=user1.getId();
      UserRoleEntity userRoleEntity= userRoleRepository.findTaskRoleIdByTaskUserId(id1);
      String name=userRoleEntity.getTask().getRole().getRoleName();
      System.out.println("Role name:"+name);
      
      
      if(name.equals("admin"))
      {
		jobEntity.setJobtitle(jobDto.getJobtitle());
		jobEntity.setLocation(jobDto.getLocation());
		
		jobRepository.save(jobEntity);
		return jobDto;
		
	  }
      else
      {
    	  throw new ResourceNotFoundException("only Admin can update job Not others !!");
      }
  }

  @Override
  public void deletejob(Long id,HttpServletRequest request)
  {
	  this.jobRepository.findById(id).orElseThrow( () ->
	  new ResourceNotFoundException("job Not Found With Id :"+id));
	  
	  final String header=request.getHeader("Authorization");
	  String requestToken=header.substring(7);

	  final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
	   
	  Users user1=userRepository.findByEmail(email);
      Long id1=user1.getId();
      UserRoleEntity userRoleEntity= userRoleRepository.findTaskRoleIdByTaskUserId(id1);
      String name=userRoleEntity.getTask().getRole().getRoleName();
      System.out.println("Role name:"+name);
      
      
      if(name.equals("admin"))
      {
	    this.jobRepository.deleteById(id);
	  }
      else
      {
    	  throw new ResourceNotFoundException("only admin  can delete job !!!");
      }
  
}
}
