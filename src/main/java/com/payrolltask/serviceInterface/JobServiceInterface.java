package com.payrolltask.serviceInterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.payrolltask.dto.JobDto;

public interface JobServiceInterface 
{
	void addjob(JobDto jobDto,HttpServletRequest request);

	Page<IJobListDto> getAllJob(String search, String pageNumber, String pageSize);

	JobDto getjobById(Long id);

	JobDto updatejob(JobDto jobDto, Long id,HttpServletRequest request);

	void deletejob(Long id,HttpServletRequest request);
}
