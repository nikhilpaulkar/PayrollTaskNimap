package com.payrolltask.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.UserJobEntity;
import com.payrolltask.serviceInterface.IEmailRecruiterListDto;
import com.payrolltask.serviceInterface.IUserJobListDto;
@Repository
public interface UserJobRepository extends JpaRepository<UserJobEntity, Long>
{

	@Query(value="select jobentity.id as jobid,jobentity.jobtitle,jobentity.location,userentity.id as userid,userentity.email ,userentity.name from userjob\r\n"
			+ "inner join jobentity on userjob.jobs_id=jobentity.id\r\n"
			+ "inner join userentity on userentity.id=userjob.user_id And(:userid=''OR userjob.user_id IN(select unnest(cast(string_to_array(:userid,',') as bigint[])))) AND\r\n"
			+ "(:jobid=''OR userjob.jobs_id IN (select unnest(cast(string_to_array(:jobid,',') as bigint[]))))",nativeQuery=true)
	Page<IUserJobListDto> findByOrderByIdDesc(String userid,String jobid,Pageable pagable, Class<IUserJobListDto> class1);

	
	@Query(value="select userentity.email from userentity  INNER join jobentity on jobentity.recruiter_id=userentity.id\r\n",nativeQuery = true)
	List<IEmailRecruiterListDto> findAllEmail(Class<IEmailRecruiterListDto> class1);

	
	
}
