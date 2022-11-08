package com.payrolltask.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.JobDto;
import com.payrolltask.entity.JobEntity;
import com.payrolltask.entity.RoleEntity;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.JobRepository;
import com.payrolltask.repository.RoleRepository;
import com.payrolltask.serviceInterface.IJobListDto;
import com.payrolltask.serviceInterface.JobServiceInterface;
import com.payrolltask.utility.Pagination;

@Service
public class JobServiceImpl implements JobServiceInterface
{
  @Autowired
  private JobRepository jobRepository;
  
  @Autowired
  private RoleRepository roleRepository;
  

  @Override
  public void addjob(JobDto jobDto) 
  {
	
	    JobEntity jobEntity=new JobEntity();
		jobEntity.setJobtitle(jobDto.getJobtitle());
		jobEntity.setLocation(jobDto.getLocation());
		
		RoleEntity role=this.roleRepository.findById(jobDto.getRecruiter()).orElseThrow(()->
        new ResourceNotFoundException("not found recruiter id"));	
		
		jobEntity.setRecruiter(role);
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
  
  
}
