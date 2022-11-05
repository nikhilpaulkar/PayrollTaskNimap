package com.payrolltask.serviceInterface;

import com.payrolltask.dto.LoggerDto;
import com.payrolltask.entity.LoggerEntity;
import com.payrolltask.entity.Users;

public interface LoggerServiceInerface 
{
	public void createLogger(LoggerDto loggerdto , Users user);

	public LoggerEntity getLoggerDetail(String requestTokenHeader);
	
	void LogoutUser(String token);

}
