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

}
