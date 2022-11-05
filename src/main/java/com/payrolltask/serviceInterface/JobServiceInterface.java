package com.payrolltask.serviceInterface;

import org.springframework.data.domain.Page;

import com.payrolltask.dto.JobDto;

public interface JobServiceInterface 
{
	void addjob(JobDto jobDto);

	Page<IJobListDto> getAllJob(String search, String pageNumber, String pageSize);

	JobDto getjobById(Long id);

	JobDto updatejob(JobDto jobDto, Long id);

	void deletejob(Long id);
}
