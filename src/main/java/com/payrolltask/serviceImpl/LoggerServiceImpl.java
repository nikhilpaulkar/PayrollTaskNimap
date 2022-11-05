package com.payrolltask.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.LoggerDto;
import com.payrolltask.entity.LoggerEntity;
import com.payrolltask.entity.Users;
import com.payrolltask.repository.LoggerRepository;
import com.payrolltask.serviceInterface.LoggerServiceInerface;

@Service
public class LoggerServiceImpl implements LoggerServiceInerface
{
	@Autowired
	private LoggerRepository loggerRepository;

	@Override
	public void createLogger(LoggerDto loggerdto, Users user)
	{
      LoggerEntity logger=new LoggerEntity();
      logger.setUserid(user);
      logger.setToken(loggerdto.getToken());
      logger.setExpiredat(loggerdto.getExpiredAt());
      loggerRepository.save(logger);

		
	}

	@Override
	public LoggerEntity getLoggerDetail(String requestTokenHeader)
	{
	   LoggerEntity logger= loggerRepository.findByToken(requestTokenHeader);
	   return logger;
	   
		
	}

	@Override
	public void LogoutUser(String token)
	{
		
		final String user=token.substring(7);
		loggerRepository.removeByToken(user);
	}

}
