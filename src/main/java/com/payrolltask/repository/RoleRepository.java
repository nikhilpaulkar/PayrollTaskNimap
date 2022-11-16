package com.payrolltask.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.payrolltask.entity.RoleEntity;
import com.payrolltask.serviceInterface.IRoleListDto;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>
{

	Page<IRoleListDto> findByOrderById(Pageable pagable, Class<IRoleListDto> class1);

	Page<IRoleListDto> findByRoleName(String search, Pageable pagable, Class<IRoleListDto> class1);

	
	
	
//	@Transactional
//	@Query(value = "select userentity.id userentity.email,userentity.name from role inner join userrole on userrole.role_id=role.id inner join userentity on userentity.id=userrole.user_id where role_name=:role_name",nativeQuery = true)
//	Page<ICandidateListDto> findByUserByCandidate(@Param("role_name")String search, Pageable pagable, Class<ICandidateListDto> class1);

	
	
	
	//Page<ICandidateListDto> findByOrderByUserId(Pageable pagable, Class<ICandidateListDto> class1);

	
	
}
