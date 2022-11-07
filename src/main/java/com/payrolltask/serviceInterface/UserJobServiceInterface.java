package com.payrolltask.serviceInterface;

import javax.servlet.http.HttpServletRequest;

import com.payrolltask.dto.UserJobDto;

public interface UserJobServiceInterface
{

	void adduserjob(UserJobDto userJobDto,HttpServletRequest request);
	
}
