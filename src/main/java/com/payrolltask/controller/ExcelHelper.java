package com.payrolltask.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.payrolltask.entity.ExcelEntity;

public class ExcelHelper
{
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static String[] HEADER = { "Id", "Name", "Email", "Jobtitle","Location" };
	public  static String SHEET = "Sheet1";

	  public static boolean hasExcelFormat(MultipartFile file) 
	  {
		 
	    if (!TYPE.equals(file.getContentType())) 
	    {
	    	
	    	
	      return false;
	    }

	    return true;
	  }
    // upload file 
	public static List<ExcelEntity> excelToTutorials(InputStream inputStream) 
	{
	
		
		try 
		{
		      Workbook workbook = new XSSFWorkbook(inputStream);
		    
		      Sheet sheet = workbook.getSheet(SHEET);
		      
		      Iterator<Row> rows = sheet.iterator();
		      

		      List<ExcelEntity> tutorials = new ArrayList<ExcelEntity>();
		      
		      int rowNumber = 0;
		      while (rows.hasNext())
		      {
		    	  
		        Row currentRow = rows.next();
		        
		       
		        if (rowNumber == 0) 
		        {
		         
		          rowNumber++;
		          continue;
		        }

		        Iterator<Cell> cellsInRow = currentRow.iterator();

		        ExcelEntity tutorial = new ExcelEntity();

		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) 
		        {
		          Cell currentCell = cellsInRow.next();

		          switch (cellIdx) 
		          {
		          

		          case 0:
		            tutorial.setId((long) currentCell.getNumericCellValue());
		            break;

		          case 1:
		            tutorial.setEmail(currentCell.getStringCellValue());
		            break;

		          case 2:
		            tutorial.setJobtitle(currentCell.getStringCellValue());
		            break;

		          case 3:
			            tutorial.setLocation(currentCell.getStringCellValue());
			            break;
			            
		          case 4: 
		        	   tutorial.setName(currentCell.getStringCellValue());
		        	   break;
		        
		          
		          default:
		            break;
		          }

		          cellIdx++;
		        }

		        tutorials.add(tutorial);
		      }

		      workbook.close();

		      return tutorials;
		    } catch (Exception e)
		   {
		      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		    }
		  }
		

}
