package com.payrolltask.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.payrolltask.entity.Users;
@Service
public class EmailServiceImpl 
{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public String sendMail(String emailTo, String subject, String text, Users users)
	{
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setFrom("nikhilpaulkar2@gmail.com");
		simpleMailMessage.setTo(users.getEmail());
		simpleMailMessage.setSubject("Apply sucess");
		simpleMailMessage.setText(" demo");
		javaMailSender.send(simpleMailMessage);
		return "Email Send Success";
	}

	public void sendSimpleMessage(String emailTo, String subject, String text)
	{

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("nikhilpaulkar2@gmail.com");

		message.setTo(emailTo);
		message.setSubject(subject);
		message.setText(text);

		javaMailSender.send(message);

	}
	
	public String mail(String emailTo, String subject, String job)
	{
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setFrom("nikhilpaulkar2@gmail.com");
		simpleMailMessage.setTo(emailTo);
		simpleMailMessage.setSubject("Apply sucess");
		simpleMailMessage.setText(" apply  job  successfully");
		javaMailSender.send(simpleMailMessage);
		return "Email Send Success";
	}


	public int generateOTP() 
	{

		int min = 100000;
		int max = 999999;

		int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
		return random_int;

	}

}
