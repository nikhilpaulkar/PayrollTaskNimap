package com.payrolltask.dto;

public class TokenKeys {
	private Long id;
	private String name;
	private String email;
	private String token;

	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

    public void setName(String name) {
		this.name = name;
	}
    public String getEmail() {
		return email;
	}

   public void setEmail(String email) {
		this.email = email;
	}
   public String getToken() {
		return token;
	}
   public void setToken(String token) {
	this.token = token;
	}
     
   
   
  
public TokenKeys(Long id, String name, String email, String token) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.token = token;
}

public TokenKeys() {
		super();
		// TODO Auto-generated constructor stub
	}
  
	
}
