package com.payrolltask.serviceInterface;

import java.util.List;


import org.springframework.data.domain.Page;

import com.payrolltask.dto.JobDto;

public interface JobServiceInterface 
{
	void addjob(JobDto jobDto,Long id);

	Page<IJobListDto> getAllJob(String search, String pageNumber, String pageSize);

	List<IJobListDto> getjobById(Long id);

	JobDto updatejob(JobDto jobDto, Long id);

	void deletejob(Long id);
	
	
	List<IRecruiterDto> getJobbyRecruiter(Long id);

    List <IListRecruiterDto> getRecruiterJobsById(Long recruiter_id);
	
}
