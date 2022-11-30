package com.payrolltask.serviceInterface;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.payrolltask.dto.ModelDto;
import com.payrolltask.entity.OtpEntity;
import com.payrolltask.entity.Users;

public interface AuthInterface
{
	public boolean comparePassword(String password, String hashpassword);

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

	Boolean updateUserwithPassword(ModelDto modelDto, Users userEntity, OtpEntity otpEntity) throws Exception;

}
