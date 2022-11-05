package com.payrolltask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.PermissionEntity;
import com.payrolltask.serviceInterface.PermissionListDto;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long>
{

	Page<PermissionListDto> findByOrderById(Pageable pagable, Class<PermissionListDto> class1);

	Page<PermissionListDto> findByActionName(String search, Pageable pagable, Class<PermissionListDto> class1);
	
	
	

}
