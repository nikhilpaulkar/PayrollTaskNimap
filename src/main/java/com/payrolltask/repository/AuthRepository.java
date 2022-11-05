package com.payrolltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.Users;


@Repository
public interface AuthRepository extends JpaRepository<Users, Long>
{

	Users findByEmail(String email);

}
