package com.payrolltask.serviceInterface;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthInterface
{
	public boolean comaparePassword(String email, String hashpassword);

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
