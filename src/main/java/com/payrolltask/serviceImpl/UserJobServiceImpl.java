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
  private EmailServiceImpl emailServiceImpl;
  
  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private JobRepository jobRepository;
  
  @Autowired
  private UserRoleRepository userRoleRepository;
  
  @Autowired
  private RoleRepository roleRepository;
  
  
  @Override
  public void adduserjob(UserJobDto userJobDto,HttpServletRequest requesst)
  {
	  Users user=userRepository.findById(userJobDto.getUserId()).orElseThrow(()-> 
	  new ResourceNotFoundException("Not Found UserId"));
		

		
		ArrayList<JobEntity> userJobs = new ArrayList<>();

		for(int i=0;i<userJobDto.getJobId().size();i++)
		{
			
			Long JobId=userJobDto.getJobId().get(i);
			System.out.println("JobId"+JobId);
			JobEntity job=jobRepository.findById(JobId).orElseThrow(()->
			new ResourceNotFoundException("Not Found Job Id"));
			
			System.out.println("Id");
			List<UserRoleEntity> userRole=userRoleRepository.findByUserId(user.getId());
			System.err.println("UserRole..."+userRole);
			
			userJobs.add(job);
			UserJobEntity userJob=new UserJobEntity();
			userJob.setJobs(job);
			userJob.setUser(user);
			userJobRepository.save(userJob);
			
			System.out.println("Size"+userRole.size());
	
			
			UserRoleEntity role=userRole.get(i);
			
			Long Id=role.getTask().getRole().getId();
			for(int i1=0;i1<Id;i1++)
			{

				System.out.println("Role"+Id.SIZE);
				
			}
			String roleName=role.getTask().getRole().getRoleName();
			System.out.println("RoleName"+roleName);
			if(roleName.equals("candidate"))
			{
				emailServiceImpl.sendSimpleMessage(user.getEmail(), "Candidate Apply sucessfully To Job", job.getJobtitle());
				
			}
			

		
			System.out.println("JobId"+JobId);

			JobEntity job1=jobRepository.findById(JobId).orElseThrow(()->
			new ResourceNotFoundException("Not Found Job Id"));
			System.out.println("jkjksd");
			RoleEntity recruiterId= job1.getRecruiter();
			System.out.println("welcom");
			System.out.println("Role"+recruiterId);
			Long recId=recruiterId.getId();
			System.out.println("hello"+recruiterId.getId());
			RoleEntity roles=roleRepository.findById(recId).orElseThrow(()->
			new ResourceNotFoundException("Not Found RoleId.."));
			//3 --Recruiter
			Long RoleId=roles.getId();
			System.out.println("RoleId"+RoleId);
			
			
			
			List<UserRoleEntity> userRoles=userRoleRepository.findRoleId(RoleId);
			
			System.out.println("UserRoles"+userRoles);
			
//			System.out.println("UserRoles"+userRoles.get(i));
			String roleName1=roles.getRoleName();
			
			
			for(int i1=0;i1<userRoles.size();i1++)
				
			{
				String email=userRoles.get(i1).getTask().getUser().getEmail();
				
				System.out.println("RoleName1..."+roleName1);

					
					if(roleName1.equals("recruiter"))
					{
						
						emailServiceImpl.sendSimpleMessage(email, "Candidate Apply sucessfully To Job", job.getJobtitle());

					}
			}

		
		}
 }

  @Override
  public Page<IUserJobListDto> getAllcandidate(String search, String pageNumber, String pageSize)
  {
		Pageable pagable=new Pagination().getPagination(pageNumber, pageSize);
		if((search=="")||(search==null)||(search.length()==0))
		{
			return userJobRepository.findByOrderByIdDesc(pagable,IUserJobListDto.class);
		}
		return null;
	
 }

	







}
