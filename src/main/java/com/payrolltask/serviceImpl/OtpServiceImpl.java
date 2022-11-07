package com.payrolltask.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payrolltask.dto.OtpDto;
import com.payrolltask.entity.OtpEntity;
import com.payrolltask.entity.Users;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.OtpRepository;


@Service
public class OtpServiceImpl 
{
  @Autowired
  private OtpRepository otpRepository;
  
 
  public OtpEntity saveotp(OtpDto otpDto,Users user) throws Exception
  {
	  try
	  {
		  OtpEntity otpEntity=this.otpRepository.findByEmail(otpDto.getEmail());
				  
		  if (otpEntity != null) 
		  {

			throw new ResourceNotFoundException("email not found");
		 }
		  else
		  {
			  OtpEntity otpEntity1= new OtpEntity();
			  
			  otpEntity1.setEmail(user.getEmail());
			  otpEntity1.setOtp(otpDto.getOtp());
			  otpEntity1.setUser(user);
			  otpEntity1.setExpiredat(otpDto.getExpiredat());
			  otpRepository.save(otpEntity1);
			  return otpEntity1;
			  
		  }
	  }catch (Exception e) 
	  {
		throw new ResourceNotFoundException("not found");
	  }
  }
  
  
  
	
}
