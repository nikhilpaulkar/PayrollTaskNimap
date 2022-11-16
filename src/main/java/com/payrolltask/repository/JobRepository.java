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
import com.payrolltask.serviceInterface.ICandidateListDto;
import com.payrolltask.serviceInterface.IJobGetListDto;
import com.payrolltask.serviceInterface.IJobListDto;
import com.payrolltask.serviceInterface.IRecruiterDto;
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

	
	@Transactional
	@Query(value="select userentity.id as userid,role.id as roleid ,jobentity.id as jobid,jobentity.jobtitle,jobentity.location from userentity INNER join userrole ON userrole.user_id=userentity.id INNER join role ON role.id=userrole.role_id INNER join jobentity ON jobentity.recruiter_id=role.id where userentity.id=:id",nativeQuery = true)
	List<IRecruiterDto> findgetJobbyRecruiter(@Param("id") Long id, Class<IRecruiterDto> class1);

	
	
	
	
	@Query(value="select userentity.id as userid,userentity.name,jobentity.id as jobid , jobentity.jobtitle,jobentity.location,userrole.role_id from  userentity INNER join userjob ON userjob.user_id = userentity.id INNER join jobentity ON jobentity.id = userjob.jobs_id INNER join userrole on userrole.user_id=userentity.id INNER join role on userrole.role_id=role.id",nativeQuery = true)
	List<ICandidateListDto> findbycandidateid(@Param("id") Long id, Class<ICandidateListDto> class1);


}
