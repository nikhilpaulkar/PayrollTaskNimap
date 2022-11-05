package com.payrolltask.controller;


import java.util.Calendar;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payrolltask.dto.ErrorResponseDto;
import com.payrolltask.dto.LoggerDto;
import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.dto.TokenKeys;
import com.payrolltask.dto.UserDto;
import com.payrolltask.entity.Users;
import com.payrolltask.repository.UserRepository;
import com.payrolltask.serviceImpl.AuthServiceImpl;
import com.payrolltask.serviceImpl.UsersServiceImpl;
import com.payrolltask.serviceInterface.LoggerServiceInerface;
import com.payrolltask.utility.PasswordValidator;
import com.payrolltask.websecurity.JwtAuthRequest;
import com.payrolltask.websecurity.JwtTokenUtil;




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
	  
	
    // Register API
	@PostMapping("/register")
	public ResponseEntity<?>createuser(@RequestBody UserDto userDto)throws Exception
	{
		   
		String email = userDto.getEmail();
		String password = userDto.getPassword();
		
		if (PasswordValidator.isValidforEmail(email))
		{
			Users user = userRepository.findByEmailContainingIgnoreCase(email);
              System.out.println("jadskj");
//			if (PasswordValidator.isValid(password))
//			{
//                 System.out.println("password");
				
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
//			}
//			else 
//			{
//
//				return ResponseEntity.ok(new ErrorResponseDto(
//						"Password should have Minimum 8 ","Password validation not done"));
//			}

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
				return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "user not found "), HttpStatus.NOT_FOUND);
			}

		}
	   
	 
	 @Transactional
	 @GetMapping("/logout")
	 public ResponseEntity<?> logoutUser(@RequestHeader("Authorization") String token)
	{
            loggerServiceInterface.LogoutUser(token);
			return new ResponseEntity<>(new ErrorResponseDto("Logout Successfully", "logout  Success"), HttpStatus.OK);
	}
	   

}
