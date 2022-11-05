package com.payrolltask.websecurity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil
{
	private static final long serialVersionUID = -2550185165626007488L;
    public static  final long JWT_TOKEN_VALIDITY=5 * 60 * 60;
    public static final long JWT_TOKEN_VALIDITY_FORGOT_PASS = 5 * 60;

    @Value("${jwt.secret}")

   private String secret;

   public String getUsernameFromToken(String token)
  {
	return getClaimFromToken(token,Claims::getSubject);

   }
    //retrieve username from jwt token
 	public String getEmailFromToken(String token) {
  
 		return getClaimFromToken(token, Claims::getSubject);

 	}

 	// retrieve expiration date from jwt token
 	public Date getExpirationDateFromToken(String token) {

 		return getClaimFromToken(token, Claims::getExpiration);

 	}

 	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

 		final Claims claims = getAllClaimsFromToken(token);
 		return claimsResolver.apply(claims);

 	}

 	// for retrieveing any information from token we will need the secret key
 	private Claims getAllClaimsFromToken(String token) {

 		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

 	}

 	// check if the token has expired
 	private Boolean isTokenExpired(String token) {

 		final Date expiration = getExpirationDateFromToken(token);
 		return expiration.before(new Date());

 	}


 	// generate token for user
 	public String generateToken(UserDetails userDetails) {

 		Map<String, Object> claims = new HashMap<>();
 		return doGenerateToken(claims, userDetails.getUsername());
 				

 	}

 	// generate token for user
 	public String generateTokenOnForgotPass(String email) {

 		Map<String, Object> claims = new HashMap<>();
 		return doGenerateTokenOnForgotPass(claims, email);

 	}


 	private String doGenerateToken(Map<String, Object> claims, String subject)
 	{
       return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY * 1000))).signWith(SignatureAlgorithm.HS512, secret).compact();
     }

 	private String doGenerateTokenOnForgotPass(Map<String, Object> claims, String subject) {

 		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + (JWT_TOKEN_VALIDITY_FORGOT_PASS * 1000))).signWith(SignatureAlgorithm.HS512, secret).compact();

 	}

 	// validate token
 	public Boolean validateToken(String token, UserDetails userDetails) {

 		return !isTokenExpired(token);


 	}
	public String extractName(String temp) {
		// TODO Auto-generated method stub
		return null;
	}


}
