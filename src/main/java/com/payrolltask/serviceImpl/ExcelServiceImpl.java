package com.payrolltask.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.payrolltask.controller.ExcelHelper;
import com.payrolltask.entity.ExcelEntity;
import com.payrolltask.repository.ExcelRepository;
import com.payrolltask.serviceInterface.ExcelInterface;

@Service
public class ExcelServiceImpl implements ExcelInterface
{

	@Autowired
	private ExcelRepository excelRepository;
	@Override
	public ExcelEntity save(MultipartFile file)
	{
		try 
	    {
	    	
	     List<ExcelEntity> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
	     excelRepository.saveAll(tutorials);
	      
	    } catch (Exception e) 
	    {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
		return null;
		
	}

}
