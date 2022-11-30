package com.payrolltask.serviceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payrolltask.entity.UserJobEntity;
import com.payrolltask.repository.ExcelRepo;
import com.payrolltask.utility.ExcelExportUtils;

@Service

public class ExcelServiceImpls 
{
	@Autowired
	private ExcelRepo excelRepo;
	
	 public List<UserJobEntity> exportCustomerToExcel(HttpServletResponse response) throws IOException
	 {
	        List<UserJobEntity> customers = excelRepo.findAll();
	        ExcelExportUtils exportUtils = new ExcelExportUtils(customers);
	        exportUtils.exportDataToExcel(response);
	        return customers;
	    }
}
