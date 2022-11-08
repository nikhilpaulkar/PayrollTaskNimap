package com.payrolltask.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.UserJobDto;
import com.payrolltask.entity.JobEntity;
import com.payrolltask.entity.RoleEntity;
import com.payrolltask.entity.UserJobEntity;
import com.payrolltask.entity.UserRoleEntity;
import com.payrolltask.entity.Users;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.JobRepository;
import com.payrolltask.repository.RoleRepository;
import com.payrolltask.repository.UserJobRepository;
import com.payrolltask.repository.UserRepository;
import com.payrolltask.repository.UserRoleRepository;
import com.payrolltask.serviceInterface.IUserJobListDto;
import com.payrolltask.serviceInterface.UserJobServiceInterface;
import com.payrolltask.utility.Pagination;

@Service
public class UserJobServiceImpl implements UserJobServiceInterface 
{
  @Autowired
  private UserJobRepository userJobRepository;

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private JobRepository jobRepository;
  
  @Autowired
  private EmailServiceImpl emailServiceImpl;
  
  @Autowired
  private RoleRepository roleRepository;
  
  @Autowired
  private  UserRoleRepository userRoleRepository;
  
  @Override
  public void adduserjob(UserJobDto userJobDto,HttpServletRequest requesst)
  {
	  
	  Users users = userRepository.findById(userJobDto.getUserId()).orElseThrow(() ->
	  new ResourceNotFoundException("user not found"));
	  
	  long id=users.getId();
      UserRoleEntity userRoleEntity= userRoleRepository.findTaskRoleIdByTaskUserId(id);
      String roleName=userRoleEntity.getTask().getRole().getRoleName();
      System.out.println("Role name:"+roleName);
      
      
	 
	  List<JobEntity> jobEntity = jobRepository.findByIdIn(userJobDto.getJobId());
		
	  if (jobEntity.size() == userJobDto.getJobId().size()) 
	  {
		 
		ArrayList<UserJobEntity> userJobList = new ArrayList<>();
			
		for (int i = 0; i < jobEntity.size(); i++)
		{
			UserJobEntity userJob = new UserJobEntity();
			
			userJob.setUser(users);
			userJob.setJobs(jobEntity.get(i));
			
			userJobList.add(userJob);
			userJobRepository.saveAll(userJobList);
				
			
			List<RoleEntity> role = this.roleRepository.findAll();
            System.out.println("what is your role");
		    if (roleName.equals("candidate")) 
			{
				System.out.println("sdjk"+users.getEmail());
				String email = users.getEmail();
				
                this.emailServiceImpl.sendMail(email, "job has been applied success", email,users);
			    
			}
//		    if (roleName.equals("recuriter"))
//			{
//		    	
//			   
//			   String emaill = users.getEmail();
//
//			   this.emailServiceImpl.sendSimpleMessage(emaill, "job has been applied success", emaill);
//					
//			}
		  }
		 
		} 
		else
		{
			new ResourceNotFoundException("not found");
		}

	}

  @Override
  public Page<IUserJobListDto> getAllcandidate(String search, String pageNumber, String pageSize)
  {
		Pageable pagable=new Pagination().getPagination(pageNumber, pageSize);
		if((search=="")||(search==null)||(search.length()==0))
		{
			return userJobRepository.findByOrderByIdAsc(pagable,IUserJobListDto.class);
		}
		return null;
	
 }

	







}
