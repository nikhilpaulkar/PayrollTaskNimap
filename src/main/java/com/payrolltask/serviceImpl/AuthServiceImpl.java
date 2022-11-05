package com.payrolltask.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payrolltask.entity.Users;
import com.payrolltask.repository.AuthRepository;
import com.payrolltask.serviceInterface.AuthInterface;

@Service
public class AuthServiceImpl implements AuthInterface
{
  @Autowired
  private AuthRepository authRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
  {
	  Users user = null;
	  user=authRepository.findByEmail(email);
	  if(user==null)
	  {
		 throw new UsernameNotFoundException("user not found with"+email);
	  }
	  
	 return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),new ArrayList<>());


	
  }


  @Override
  public boolean comaparePassword(String email, String hashpassword) 
  {
	
	return passwordEncoder.matches(hashpassword, hashpassword);
  }


public AuthServiceImpl() {
	super();
	// TODO Auto-generated constructor stub
}
  
  
  
  
  
  
  
  
  
  
  
  
}
