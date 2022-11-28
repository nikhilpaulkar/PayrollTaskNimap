package com.payrolltask.utility;

public class ApiUrls 
{
	public static final String API = "/api";
	public static final String GET_ALL = "/all";
	
	public static final String JOB = "/job";

	public static final String AUTH = "/auth";
	
	
	public static final String[] SWAGGER_URLS = { "/v3/api-docs", "/v2/api-docs", "/swagger-resources/**",
			"/swagger-ui/**", "/webjars/**", "/api/swagger-ui/index.html" };

	public static final String[] URLS_WITHOUT_HEADER = { ApiUrls.API, ApiUrls.API ,
			ApiUrls.API  };

}
