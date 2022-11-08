package com.payrolltask.serviceInterface;

import com.payrolltask.entity.JobEntity;
import com.payrolltask.entity.Users;

public interface IUserJobListDto
{
	public Long getId() ;
	public Users getUser();
	public JobEntity getJobs();
}
