package com.friday.test.Fridayskillassesment.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ReadDatasheet {


	private static Logger logger = Logger.getLogger(ReadDatasheet.class.getName());
	public static LinkedHashMap<String,String> datamap;
	public String testdatasheet= System.getProperty("user.dir")+"/src/test/resources/Testdata/Testdata.xlsx";
	public String testsuite = System.getProperty("user.dir")+"/src/test/resources/Testsuite.xlsx";
	public String TestDataSheet;
	public String testcasename;
	
	public ReadDatasheet(String testcasename){
		
		this.testcasename = testcasename;
		datamap = new LinkedHashMap<String, String>();
		gettestcasedatasheet();
		getTCdata(System.getProperty("user.dir")+"/src/test/resources/Testdata/Testdata.xlsx");
		
	}
	
	
	public void gettestcasedatasheet(){
		FileInputStream file = null;
		Workbook workBook = null;
		try {
			file = new FileInputStream(new File(testsuite));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			workBook = new XSSFWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Sheet sheet = workBook.getSheet("TestSuite");
		if (sheet == null) {
			logger.info("Sheet '" + "TestSuite" + "' is not found.");
			logger.error("Sheet '" + "TestSuite" + "' is not found.");
            IllegalArgumentException e = new IllegalArgumentException("Sheet '" + "TestSuite" + "' is not found.");
            
            throw e;
        }
		Iterator<Row> rowiterator = sheet.iterator();
		while(rowiterator.hasNext()){
			Row row = rowiterator.next();
			String tcname = getRichString(row.getCell(1));
			if(tcname.equalsIgnoreCase(testcasename)){
				TestDataSheet = getRichString(row.getCell(2));
			}
			
		}
		
	}
	
	
 public void getTCdata(String filepath){
		
		FileInputStream file = null;
		Workbook workBook = null;
		try {
			file = new FileInputStream(new File(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			workBook = new XSSFWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Sheet sheet = workBook.getSheet(TestDataSheet);
		if (sheet == null) {
			logger.info("Sheet '" + "TestDataSheet" + "' is not found.");
			logger.error("Sheet '" + "TestDataSheet" + "' is not found.");
            IllegalArgumentException e = new IllegalArgumentException("Sheet '" + "TestDataSheet" + "' is not found.");
            
            throw e;
        }
		Iterator<Row> rowiterator = sheet.iterator();
		int i=0;
		while(rowiterator.hasNext()){
			Row row = rowiterator.next();
			String address = getRichString(row.getCell(0));
			if(i!=0){
				datamap.put("data"+i, address);
			}
			i++;
		}
		
		//System.out.println(datamap);
	}


	public String getRichString(Cell cell){
	
			String cellvalue = null ; 
		switch(cell.getCellType()){
			case Cell.CELL_TYPE_NUMERIC:
		    	cellvalue = (String.valueOf((int)cell.getNumericCellValue()));
			 break;
		    case Cell.CELL_TYPE_STRING:
		    	cellvalue = cell.getStringCellValue();
		     break;
		    case Cell.CELL_TYPE_BLANK:
		    	cellvalue =null;
		     break;
		   }
			
			return cellvalue;
		}	
}
