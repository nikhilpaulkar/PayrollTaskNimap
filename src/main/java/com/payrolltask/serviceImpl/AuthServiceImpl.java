package com.payrolltask.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payrolltask.cacheconfig.CacheOperation;
import com.payrolltask.dto.ModelDto;
import com.payrolltask.entity.OtpEntity;
import com.payrolltask.entity.Users;
import com.payrolltask.repository.AuthRepository;
import com.payrolltask.serviceInterface.AuthInterface;
import com.payrolltask.serviceInterface.RoleServiceInterface;

@Service
public class AuthServiceImpl implements AuthInterface
{
  @Autowired
  private AuthRepository authRepository;
  
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @Autowired
  private RoleServiceInterface roleServiceInterface;
  
  @Autowired
  private CacheOperation cache;

  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
  {
	  Users user = null;
	  user=authRepository.findByEmail(email);
	  if(user==null)
	  {
		 throw new UsernameNotFoundException("user not found with"+email);
	  }
	  
	 return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),getAuthority(user));


	
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


  @Override
  public Boolean updateUserwithPassword(ModelDto modelDto, Users userEntity, OtpEntity otpEntity) throws Exception 
  {
	userEntity.setPassword(passwordEncoder.encode(modelDto.getPassword()));
	this.authRepository.save(userEntity);

	return true;
	
 }
  
  
  @SuppressWarnings("unchecked")
private ArrayList<SimpleGrantedAuthority> getAuthority(Users user) 
  {

		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

		//if (!cache.isKeyExist(user.getId() + "permission", user.getId() + "permission"))
		//{
		

			ArrayList<SimpleGrantedAuthority> authorities1 = new ArrayList<>();
			ArrayList<String> permissions = roleServiceInterface.getPermissionByUserId(user.getId());
			permissions.forEach(permission ->
			{
                
				authorities1.add(new SimpleGrantedAuthority("ROLE_" + permission));
               
			});
			authorities = authorities1;
			
			cache.addInCache(user.getId() + "permission", user.getId() + "permission", authorities1);
          
		//} 
       //else 
	   //{
             
			authorities = (ArrayList<SimpleGrantedAuthority>) cache.getFromCache(user.getId() + "permission", user.getId() + "permission");
           
		//}

		return authorities;
               
	}
	
	
	
  
  
  
  
  
  
  
  
  
}
