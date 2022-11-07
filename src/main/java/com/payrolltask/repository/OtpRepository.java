package com.payrolltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payrolltask.entity.OtpEntity;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity,Long >
{

	OtpEntity findByEmail(String email);
	OtpEntity findByOtp(Integer otp);

}
