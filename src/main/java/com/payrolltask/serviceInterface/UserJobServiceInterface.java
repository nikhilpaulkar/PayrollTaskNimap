package com.payrolltask.serviceInterface;


import org.springframework.data.domain.Page;

import com.payrolltask.dto.UserJobDto;

public interface UserJobServiceInterface
{

	void applyjob(UserJobDto userJobDto,Long id);
	Page<IUserJobListDto> getAllcandidate(String search, String pageNumber, String pageSize,String userid,String jobid);
	
	
}
