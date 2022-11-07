package com.payrolltask.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.payrolltask.serviceInterface.UserJobServiceInterface;

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
	  
	  final String url = "job has been successfully applied";

	  List<JobEntity> jobEntity = jobRepository.findById(userJobDto.getJobId());
		
	  if (jobEntity.size() == userJobDto.getJobId().size()) 
	  {
//		ArrayList<UserJobEntity> userJobList = new ArrayList<>();
			
		for (int i = 0; i < jobEntity.size(); i++)
		{
//			UserJobEntity userJob = new UserJobEntity();
			
//			userJob.setUser(users);
//			userJob.setJobs(jobEntity.get(i));
//			userJobList.add(userJob);
//			userJobRepository.saveAll(userJobList);
				
//			String a = users.getEmail();

//			this.emailServiceImpl.sendSimpleMessage(users.getEmail(), "subject", url);
//				
//			List<RoleEntity> role = this.roleRepository.findAll();
//
//			String roleName = role.get(1).getRoleName();
			
//			String role1 = role.get(2).getRoleName();
				

			if (roleName.equals("recruiter")) 
			{
				Long li = role.get(1).getId();
//
		//		UserRoleEntity userrole = this.userRoleRepository.findTaskRoleIdByTaskUserId(li);

//				String email = userrole.getUsers().getEmail();
					
//               this.emailServiceImpl.sendSimpleMessage(email, "subject", url);
			}

			else if (role.equals("candidate"))
			{
//			 Long l = role.get(2).getId();
//
//			 UserRoleEntity role = this.userRoleRepository.findTaskRoleIdByTaskUserId(l);
			 
			 
//			 String email = role.getUsers().getEmail();
//
	//		 this.emailServiceImpl.sendSimpleMessage(email, "subject", url);
//					
			}
		  }
		} 
		else
		{
			new ResourceNotFoundException("not found");
		}

	}

	







}
