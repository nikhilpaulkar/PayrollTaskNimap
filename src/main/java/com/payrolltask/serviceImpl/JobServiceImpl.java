package com.payrolltask.serviceImpl;

import java.util.List;
import java.util.Optional;

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
import com.payrolltask.repository.UserRepository;
import com.payrolltask.repository.UserRoleRepository;
import com.payrolltask.serviceInterface.ICandidateListDto;
import com.payrolltask.serviceInterface.IJobGetListDto;
import com.payrolltask.serviceInterface.IJobListDto;
import com.payrolltask.serviceInterface.IRecruiterDto;
import com.payrolltask.serviceInterface.IRecruiterJobListDto;
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

 
  @Override
  public void addjob(JobDto jobDto,HttpServletRequest request) 
  {
	
	  
	  final String header=request.getHeader("Authorization");
	  String requestToken=header.substring(7);

	  final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
	   
	  Users user1=userRepository.findByEmail(email);
	  System.out.println("UserID"+user1);
      Long id=user1.getId();
      UserRoleEntity userRoleEntity= userRoleRepository.findTaskRoleIdByTaskUserId(id);
     
      
      String roleName=userRoleEntity.getTask().getRole().getRoleName();
      System.out.println("Role name:"+roleName);
      
      
        if(roleName.equals("recruiter"))
        {
	    
	     JobEntity jobEntity=new JobEntity();
		 jobEntity.setJobtitle(jobDto.getJobtitle());
		
		 jobEntity.setLocation(jobDto.getLocation());
		
		  jobEntity.setRecruiter(user1);
		 System.out.println("REcruiter"+user1);
		 
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
  public List<IJobGetListDto> getjobById(Long id)
  {
	   JobEntity jobEntity=jobRepository.findById(id).orElseThrow(()->
	   new ResourceNotFoundException("job id not found"));
	  
	  
		List<IJobGetListDto> jobDtos=jobRepository.findById(id,IJobGetListDto.class);
		return jobDtos ;
	
      
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


  @Override
  public List<IRecruiterJobListDto> getJobbyRecruiterId(HttpServletRequest request) 
  {
	  
	  final String header=request.getHeader("Authorization");
	  String requestToken=header.substring(7);

	  final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
	   
	  Users user1=userRepository.findByEmail(email);
      Long id1=user1.getId();
      UserRoleEntity userRoleEntity= userRoleRepository.findTaskRoleIdByTaskUserId(id1);
      String name=userRoleEntity.getTask().getRole().getRoleName();
      
      Long roleid= userRoleEntity.getTask().getRole().getId();
      System.out.println("Role Id="+roleid);
      System.out.println("Role name:"+name);
      
      List<JobEntity> job=jobRepository.findByRecruiterById(roleid);
       new ResourceNotFoundException("not found recruiter id");
      System.out.println("recriter id"+job);
      if(name.equals("recruiter"))
      {
    	  
          List<IRecruiterJobListDto> recruiterid=jobRepository.findByRecruiterId(roleid);  
         return recruiterid;
      }   
      else
      {
    	   throw    new ResourceNotFoundException("only recruiter can get job list !!!");
    	   
      }
	
	
  }

  @Override
  public List<IRecruiterDto> getJobbyRecruiter(Long id,HttpServletRequest request)
  {
	  final String header=request.getHeader("Authorization");
	  String requestToken=header.substring(7);

	  final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
	   
	  Users user1=userRepository.findByEmail(email);
	  System.out.println("use="+user1);
      Long id1=user1.getId();
      UserRoleEntity userRoleEntity= userRoleRepository.findTaskRoleIdByTaskUserId(id1);
      String name=userRoleEntity.getTask().getRole().getRoleName();
      
      Long roleid= userRoleEntity.getTask().getRole().getId();
      System.out.println("Role Id="+roleid);
      System.out.println("Role name:"+name);
      
      if(name.equals("recruiter"))
      {
    		 List<IRecruiterDto> list= jobRepository.findgetJobbyRecruiter(id1,IRecruiterDto.class);
             System.out.println("Recruiter job List="+list);
    		 return list;
             
      }
      else
      {
    	  throw new ResourceNotFoundException("only recruiter can get list !!");
      }
	
  }
  
  
      

	@Override
	public List<ICandidateListDto> getJobbycandidateid(Long id, HttpServletRequest request)
	{
		final String header=request.getHeader("Authorization");
  	    String requestToken=header.substring(7);

  	    final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
  	   
  	    Users user1=userRepository.findByEmail(email);
        Long id1=user1.getId();
        UserRoleEntity userRoleEntity= userRoleRepository.findTaskRoleIdByTaskUserId(id1);
        String name=userRoleEntity.getTask().getRole().getRoleName();
        
        Long roleid= userRoleEntity.getTask().getRole().getId();
        System.out.println("Role Id="+roleid);
        System.out.println("Role name:"+name);
        
        if(name.equals("candidate"))
        {
      		 List<ICandidateListDto> list= jobRepository.findbycandidateid(id1,ICandidateListDto.class);
               System.out.println("Recruiter job List="+list);
      		 return list;
               
        }
        else
        {
      	  throw new ResourceNotFoundException("only candidate can get list !!");
        }
	}
  
  
}
