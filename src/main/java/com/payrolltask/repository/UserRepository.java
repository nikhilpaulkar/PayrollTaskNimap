package com.payrolltask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.payrolltask.entity.Users;
import com.payrolltask.serviceInterface.IUserListDto;

public interface UserRepository extends JpaRepository<Users, Long>
{
	Users findByEmailContainingIgnoreCase(String email);

	Users findByEmail(String email);

	Page<IUserListDto> findByOrderById(Pageable pagable, Class<IUserListDto> class1);

	Page<IUserListDto> findByName(String search, Pageable pagable, Class<IUserListDto> class1);

	
	
}
