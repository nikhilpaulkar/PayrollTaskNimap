package com.payrolltask.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.RolePermissionEntity;

@Repository
public interface RolePemissionRespository extends JpaRepository<RolePermissionEntity, Long>
{
	// update for role id and permission id 
	@Transactional
	@Modifying(flushAutomatically = true,clearAutomatically = true)
	@Query(value="UPDATE role_permission p SET permission_id=:permission_id WHERE p.role_id=:role_id",nativeQuery = true)
	void updateRolePermission(@Param ("role_id") Long  role_id,@Param ("permission_id") Long permission_id);

	
}
