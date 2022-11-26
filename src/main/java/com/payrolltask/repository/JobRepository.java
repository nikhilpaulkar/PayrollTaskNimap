package com.payrolltask.repository;


import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.JobEntity;
import com.payrolltask.serviceInterface.IJobListDto;
import com.payrolltask.serviceInterface.IRecruiterDto;
@Repository
public interface JobRepository extends JpaRepository<JobEntity,Long>
{

	Page<IJobListDto> findByOrderByIdDesc(Pageable pagable, Class<IJobListDto> class1);

	Page<IJobListDto> findByjobtitle(String search, Pageable pagable, Class<IJobListDto> class1);


	

	List<IJobListDto> findById(Long id, Class<IJobListDto> class1);

	
   	@Query(value="SELECT * from jobentity t WHERE t.recruiter_id=:recruiter_id",nativeQuery = true)
	List<JobEntity> findByRecruiterById(@Param ("recruiter_id")Long recruiter_id);

	
	
	@Query(value="select userentity.id as userid,role.id as roleid ,jobentity.id as jobid,jobentity.jobtitle,jobentity.location from userentity INNER join userrole ON userrole.user_id=userentity.id INNER join role ON role.id=userrole.role_id INNER join jobentity ON jobentity.recruiter_id=role.id where userentity.id=:id",nativeQuery = true)
	List<IRecruiterDto> findgetJobbyRecruiter(@Param("id") Long id, Class<IRecruiterDto> class1);

	
	
	
}
