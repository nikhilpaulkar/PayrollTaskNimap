package com.payrolltask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.payrolltask.dto.ErrorResponseDto;
import com.payrolltask.dto.SucessResponseDto;
import com.payrolltask.serviceInterface.ExcelInterface;

@RestController
@RequestMapping("/excel")
public class ExcelController 
{
	@Autowired
	private ExcelInterface excelInterface;

	 @PostMapping()
	  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) 
	  {
	  
		
		if (ExcelHelper.hasExcelFormat(file))
		{
		try
		{
				    	
	    excelInterface.save(file);
				    
		return new ResponseEntity<>(new SucessResponseDto("success","upload","Successfully Uploaded"),HttpStatus.OK);
		} catch (Exception e) 
	    {
				       
		return new ResponseEntity<>(new ErrorResponseDto("wrong entered fil","you entered wrong order fileds in excel file "),HttpStatus.BAD_REQUEST);
	   }
      }

	     return new ResponseEntity<>(new ErrorResponseDto("upload excel file","Please upload an excel file.."),HttpStatus.BAD_REQUEST);

	  }
	 

}
