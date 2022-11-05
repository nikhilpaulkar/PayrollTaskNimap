package com.payrolltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payrolltask.entity.LoggerEntity;


public interface LoggerRepository extends JpaRepository<LoggerEntity, Long>
{

	LoggerEntity findByToken(String requestTokenHeader);

	void removeByToken(String user);
	
}
