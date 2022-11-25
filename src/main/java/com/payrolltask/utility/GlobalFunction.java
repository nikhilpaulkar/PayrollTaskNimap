package com.payrolltask.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.payrolltask.entity.Users;
import com.payrolltask.repository.UserRepository;
import com.payrolltask.websecurity.JwtTokenUtil;
@Component
public class GlobalFunction implements HandlerInterceptor
{

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public final static String USER_ID = "X-user-id";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
		String authHeader = request.getHeader("Authorization");
		String tokenString = (null != authHeader) ? authHeader.split(" ")[1] : null;
	
			if (null != tokenString) 
			{
				final String emailString = jwtTokenUtil.getUsernameFromToken(tokenString);
				System.out.println("EmailString"+emailString);
			
				Users user = userRepository.findByEmail(emailString);

					
			  request.setAttribute("X-user-id", user.getId());

			
	}	
			return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
