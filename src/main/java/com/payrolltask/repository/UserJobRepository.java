package com.payrolltask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.UserJobEntity;
import com.payrolltask.serviceInterface.IUserJobListDto;
@Repository
public interface UserJobRepository extends JpaRepository<UserJobEntity, Long>
{

	Page<IUserJobListDto> findByOrderByIdDesc(Pageable pagable, Class<IUserJobListDto> class1);

}
