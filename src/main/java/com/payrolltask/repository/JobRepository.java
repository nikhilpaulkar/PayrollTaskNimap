package com.payrolltask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.JobEntity;
import com.payrolltask.serviceInterface.IJobListDto;
@Repository
public interface JobRepository extends JpaRepository<JobEntity,Long>
{

	Page<IJobListDto> findByOrderById(Pageable pagable, Class<IJobListDto> class1);

	Page<IJobListDto> findByjobtitle(String search, Pageable pagable, Class<IJobListDto> class1);

}
