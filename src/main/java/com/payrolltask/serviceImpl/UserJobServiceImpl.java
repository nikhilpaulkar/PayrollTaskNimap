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
import com.payrolltask.websecurity.JwtTokenUtil;

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
  
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  
  @Override
  public void adduserjob(UserJobDto userJobDto,HttpServletRequest requesst)
  {
	
		
	  final String header=requesst.getHeader("Authorization");
	  String requestToken=header.substring(7);
	  
	  final String email=jwtTokenUtil.getUsernameFromToken(requestToken);
	   
	  Users user1=userRepository.findByEmail(email);
		
		ArrayList<JobEntity> userJobs = new ArrayList<>();
		
		for(int i=0;i<userJobDto.getJobId().size();i++)
		{
			
			Long JobId=userJobDto.getJobId().get(i);
			
			JobEntity job=jobRepository.findById(JobId).orElseThrow(()->
			new ResourceNotFoundException("Not Found Job Id"));
			
			
			List<UserRoleEntity> userRole=userRoleRepository.findByUserId(user1.getId());
			
			userJobs.add(job);
			UserJobEntity userJob=new UserJobEntity();
			userJob.setJobs(job);
			userJob.setUser(user1);
			userJobRepository.save(userJob);
			
			
			UserRoleEntity role=userRole.get(i);
			
			Long Id=role.getTask().getRole().getId();
			
			List<UserRoleEntity> user11=userRoleRepository.findByRoleId(Id);

			String emails = null;
			for(int i1=0;i1<user11.size();i1++)
			{
				UserRoleEntity email1=user11.get(i);
				 emails=email1.getTask().getUser().getEmail();
			}
			String roleName=role.getTask().getRole().getRoleName();
			
			System.out.println("RoleName:"+roleName);
			if(roleName.equals("candidate"))
			{
				emailServiceImpl.mail(user1.getEmail(), "apply  job  successfully",job.getJobtitle());
				
			}
			

			JobEntity job1=jobRepository.findById(JobId).orElseThrow(()->
			new ResourceNotFoundException("Not Found Job Id"));
			
			RoleEntity recruiterId= job1.getRecruiter();
		
			
			Long recId=recruiterId.getId();
			
			RoleEntity roles=roleRepository.findById(recId).orElseThrow(()->
			new ResourceNotFoundException("Not Found RoleId.."));
			
			Long RoleId=roles.getId();
			
		    List<UserRoleEntity> userRoles=userRoleRepository.findRoleId(RoleId);
			
			
			String roleName1=roles.getRoleName();
			
			
			for(int i1=0;i1<userRoles.size();i1++)
				
			{
				
				String email2=userRoles.get(i1).getTask().getUser().getEmail();
				System.out.println("recuiter=="+roleName1);
					if(roleName1.equals("recruiter"))
					{
						
						emailServiceImpl.mail(email2, "apply job successfully", job1.getJobtitle());

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
