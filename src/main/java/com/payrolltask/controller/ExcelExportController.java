package com.payrolltask.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payrolltask.serviceImpl.ExcelServiceImpls;
@RestController
@RequestMapping("/api")
public class ExcelExportController 
{
	@Autowired
	private ExcelServiceImpls excelServiceImpls;

	@PreAuthorize("hasRole('admingetlist')")
	@GetMapping("/export-to-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException
	{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Customers_Information.xlsx";
        response.setHeader(headerKey, headerValue);
        excelServiceImpls.exportCustomerToExcel(response);
     }
}
