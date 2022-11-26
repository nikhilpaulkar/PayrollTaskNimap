package com.payrolltask.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.UserJobEntity;
import com.payrolltask.serviceInterface.IUserJobListDto;
@Repository
public interface UserJobRepository extends JpaRepository<UserJobEntity, Long>
{

	@Query(value="select jobentity.id as jobid,jobentity.jobtitle,jobentity.location,userentity.id as userid,userentity.email,userentity.name from userjob\r\n"
			+ "inner join jobentity on userjob.jobs_id=jobentity.id\r\n"
			+ "inner join userentity on userentity.id=userjob.user_id AND\r\n"
			+ "(:userid=''OR userjob.user_id IN(select unnest(cast(string_to_array(:userid,',') as bigint[]))))"+"AND\r\n"
			+ "(:jobid='' OR userjob.jobs_id IN(select unnest(cast(string_to_array(:jobid,',') as bigint[]))))AND userjob.isactive=true",nativeQuery=true)
	Page<IUserJobListDto> findListUserJob(String userid,String jobid,Pageable pagable, Class<IUserJobListDto> class1);

	
	
	
}
