package com.payrolltask.controller;


import java.util.Calendar;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payrolltask.dto.ErrorResponseDto;
import com.payrolltask.dto.LoggerDto;
import com.payrolltask.dto.ModelDto;
import com.payrolltask.dto.OtpDto;
import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.dto.TokenKeys;
import com.payrolltask.dto.UserDto;
import com.payrolltask.entity.OtpEntity;
import com.payrolltask.entity.Users;
import com.payrolltask.exception.ResourceNotFoundException;
import com.payrolltask.repository.AuthRepository;
import com.payrolltask.repository.OtpRepository;
import com.payrolltask.repository.UserRepository;
import com.payrolltask.serviceImpl.AuthServiceImpl;
import com.payrolltask.serviceImpl.EmailServiceImpl;
import com.payrolltask.serviceImpl.OtpServiceImpl;
import com.payrolltask.serviceImpl.UsersServiceImpl;
import com.payrolltask.serviceInterface.LoggerServiceInerface;
import com.payrolltask.utility.PasswordValidator;
import com.payrolltask.websecurity.JwtAuthRequest;
import com.payrolltask.websecurity.JwtTokenUtil;



@Validated
@RestController
@RequestMapping("/auth")
public class AuthController 
{
	@Autowired
	private UsersServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	  
	@Autowired
	private LoggerServiceInerface loggerServiceInterface;
	
	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	@Autowired
	private OtpServiceImpl otpServiceImpl;
	
	@Autowired
	private OtpRepository otpRepository;
	
	@Autowired
	private AuthRepository authRepository;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	  
	
    // Register API
	@PostMapping("/register")
	public ResponseEntity<?>createuser(@Valid @RequestBody UserDto userDto)throws Exception
	{
		   
		String email = userDto.getEmail();
		String password = userDto.getPassword();

		if (PasswordValidator.isValidforEmail(email))
		{
			Users user = userRepository.findByEmailContainingIgnoreCase(email);
             
		if (PasswordValidator.isValid(password))
		{
               
				if (user == null)
				{
					this.userServiceImpl.createuser(userDto);
					return new ResponseEntity<>(new SucessResponseDto("User Added", "success", "successfully Added"),HttpStatus.CREATED);

				}
				else 
				{
					return new ResponseEntity<>(
							new ErrorResponseDto("User Email Id Already Exist", "please enter new email"),
							HttpStatus.BAD_REQUEST);
				}
			}
			else 
			{

				return new  ResponseEntity<>(new ErrorResponseDto(
						"Password should have Minimum 8 charaters, one lowercase letter, one number and one special character and no white spaces","Password validation not done"),HttpStatus.BAD_REQUEST);
			}

		} 
		else 
		{
			return new ResponseEntity<>(new ErrorResponseDto("please check Email is not valid ", "Enter valid email"),
					HttpStatus.BAD_REQUEST);
    }

 	 }
	
	
	 // Login API
	 @PostMapping("/login")
	 public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthRequest authenticationRequest)
		throws Exception 
	    {

			try 
			{
	            Users user = userServiceImpl.FindByEmail(authenticationRequest.getEmail());
				
				
				if (authServiceImpl.comaparePassword(user.getPassword(), authenticationRequest.getPassword()));
				{
					UserDetails userDetails=this.authServiceImpl.loadUserByUsername(authenticationRequest.getEmail());
				   
					
					String token = jwtTokenUtil.generateToken(userDetails);
				    LoggerDto logger = new LoggerDto();
					logger.setToken(token);
					Calendar calender = Calendar.getInstance();
					calender.add(Calendar.MINUTE, 5);
					logger.setExpiredAt(calender.getTime());
					loggerServiceInterface.createLogger(logger, user);
					return ResponseEntity.ok(new SucessResponseDto("Login successfully", "success", new TokenKeys(user.getId(),user.getName(),user.getEmail(), token)));
					
				}
				
			} catch (BadCredentialsException e) 
			{
				
				throw new Exception("invalid email or password");
				
			} catch (Exception e)
			{
				return new ResponseEntity<>(new ErrorResponseDto("invalid email or password", "not found "), HttpStatus.NOT_FOUND);
			}

		}
	   
	 //logout API
	 @Transactional
	 @GetMapping("/logout")
	 public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String token)
	{
            loggerServiceInterface.LogoutUser(token);
			return new ResponseEntity<>(new ErrorResponseDto("Logout Successfully", "logout  Success"), HttpStatus.OK);
	}
	   
	 //forgot password API Send OTP
	 @PostMapping("/forgotpassword")
	 public ResponseEntity<?>forgotpassword(@Valid @RequestBody OtpDto otpDto, HttpServletRequest request)
	 {
		 try
		 {
			 Users user=authRepository.findByEmail(otpDto.getEmail());
			 final int otp=emailServiceImpl.generateOTP();
			 final String url = "OTP For Forgot Email Is-" + otp;
			 
			 Calendar calender = Calendar.getInstance();
			 
			 calender.add(Calendar.MINUTE, 5);
			 
			 otpDto.setOtp(otp);

			 otpDto.setExpiredat(calender.getTime());
             
			 this.otpServiceImpl.saveotp(otpDto, user);
			 this.emailServiceImpl.sendSimpleMessage(user.getEmail(), "abc", url);
			 return ResponseEntity
			 .ok(new SucessResponseDto("OTP send on email", "OTP send succesfully", "success"));

		 }catch (Exception e)
		 {
			 
				return ResponseEntity.ok(new ErrorResponseDto("Email not found", "not found"));

		 }
	 }

	 //Update password Using OTP 
	 @PutMapping("/forgotpasswordconfirm")
	 public ResponseEntity<?> createforgotpasswordconfirm(@RequestBody ModelDto modelDto) throws Exception
	 {

	 try 
	 {
		Users user = this.authRepository.findByEmail(modelDto.getEmail());

		if (user == null)
		{
			return new ResponseEntity<>( new ErrorResponseDto("Invalid Email","please check entered Email"),HttpStatus.NOT_FOUND);
		}

		OtpEntity otpEntity = this.otpRepository.findByOtp(modelDto.getOtp());

		if (otpEntity != null)
		{
			Users users = this.authRepository.findById(otpEntity.getUser().getId())
			.orElseThrow(() -> new ResourceNotFoundException("user not found"));
		}

		else
		{
			throw new Exception("otp not found");
		}
		try
		{
			String password = modelDto.getPassword();

			{
				if (PasswordValidator.isValid(password))
				{

				 }
				else
				{
					throw new Exception("not found");
				}
				}
		}

		catch (Exception e)
		 {
					return ResponseEntity.ok(new ErrorResponseDto(
					 "Password should have Minimum 8","Password validation not done"));
		 }

		 this.authServiceImpl.updateUserwithPassword(modelDto, user, otpEntity);
		 return new ResponseEntity<>(new SucessResponseDto("update", "update sucessfully", "updated"),HttpStatus.OK);
		} catch (Exception e)
	    {
		 return new ResponseEntity<>( new ErrorResponseDto("Invalid Otp","please check entered otp"),HttpStatus.NOT_FOUND);
	    }
	}
}
