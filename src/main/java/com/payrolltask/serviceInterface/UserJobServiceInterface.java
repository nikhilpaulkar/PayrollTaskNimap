package com.payrolltask.serviceInterface;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.payrolltask.dto.UserJobDto;

public interface UserJobServiceInterface
{

	void adduserjob(UserJobDto userJobDto,HttpServletRequest request);
	Page<IUserJobListDto> getAllcandidate(String search, String pageNumber, String pageSize);
    
	
}
