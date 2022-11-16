package com.payrolltask.repository;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.UserRoleEntity;
import com.payrolltask.serviceInterface.IUserRoleListDto;
import com.payrolltask.serviceInterface.RoleIdListDto;


@Repository
public interface UserRoleRepository extends JpaRepository< UserRoleEntity, Long>
{

	// update query for user id and role id
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query(value="update userrole u SET role_id=:role_id WHERE u.user_id=:user_id", nativeQuery=true)
	void updateuserrole (@Param("user_id")Long user_id,@Param("role_id")Long role_id);

	UserRoleEntity findTaskRoleIdByTaskUserId(long id);

	@Transactional
	@Query(value="SELECT * from userrole t WHERE t.user_id=:user_id",nativeQuery = true)
	List<UserRoleEntity> findByUserId(@Param("user_id")Long user_id);

    @Transactional
	@Query(value="SELECT * from userrole t WHERE t.role_id=:role_id",nativeQuery = true)
	List<UserRoleEntity> findRoleId(@Param ("role_id")Long role_id);

    @Transactional
   	@Query(value="SELECT * from userrole t WHERE t.role_id=:role_id",nativeQuery = true)
	List<UserRoleEntity> findByRoleId(@Param ("role_id")Long role_id);

    
	
	ArrayList<RoleIdListDto> findByTaskUserId(Long id, Class<RoleIdListDto> class1);

	@Transactional
	@Query(value="select userentity.id as userId,userentity.name as userName,userentity.email,role.id as roleId,role.role_name from role "
			+ " join userrole on userrole.role_id=role.id "
			+ " join userentity on userrole.user_id=userentity.id",nativeQuery = true)
	Page<IUserRoleListDto> findAll(Pageable pagable, Class<IUserRoleListDto> class1);

	
	
	
	

}
