package com.payrolltask.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.JobEntity;
import com.payrolltask.serviceInterface.IJobGetListDto;
import com.payrolltask.serviceInterface.IJobListDto;
import com.payrolltask.serviceInterface.IRecruiterJobListDto;
@Repository
public interface JobRepository extends JpaRepository<JobEntity,Long>
{

	Page<IJobListDto> findByOrderByIdDesc(Pageable pagable, Class<IJobListDto> class1);

	Page<IJobListDto> findByjobtitle(String search, Pageable pagable, Class<IJobListDto> class1);


	

	List<IJobGetListDto> findById(Long id, Class<IJobGetListDto> class1);

	@Transactional
   	@Query(value="SELECT * from jobentity t WHERE t.recruiter_id=:recruiter_id",nativeQuery = true)
	List<JobEntity> findByRecruiterById(@Param ("recruiter_id")Long recruiter_id);

	
	
	
	@Transactional
	@Query(value = "select jobentity.jobtitle, jobentity.location, jobentity.id as jobid ,userentity.id as userid, userentity.name ,userentity.email from jobentity inner join userjob on jobentity.id=userjob.jobs_id inner join userentity on userentity.id=userjob.user_id where jobentity.recruiter_id=:recruiter_id",nativeQuery = true)
	List<IRecruiterJobListDto> findByRecruiterId(@Param ("recruiter_id")Long recruiter_id);


}
