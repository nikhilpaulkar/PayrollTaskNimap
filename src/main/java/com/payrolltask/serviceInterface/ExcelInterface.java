package com.payrolltask.serviceInterface;

import org.springframework.web.multipart.MultipartFile;

import com.payrolltask.entity.ExcelEntity;

public interface ExcelInterface 
{
	ExcelEntity save(MultipartFile file);
}
