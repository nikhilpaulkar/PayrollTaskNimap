package com.payrolltask.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.UserRoleEntity;


@Repository
public interface UserRoleRepository extends JpaRepository< UserRoleEntity, Long>
{

	// update query for user id and role id
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query(value="update userrole u SET role_id=:role_id WHERE u.user_id=:user_id", nativeQuery=true)
	void updateuserrole (@Param("user_id")Long user_id,@Param("role_id")Long role_id);

	UserRoleEntity findTaskRoleIdByTaskUserId(long id);

	

}
