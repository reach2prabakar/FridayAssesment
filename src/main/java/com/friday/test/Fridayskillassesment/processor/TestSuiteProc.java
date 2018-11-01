package com.friday.test.Fridayskillassesment.processor;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;


public class TestSuiteProc {

	private static Logger logger = Logger.getLogger(TestSuiteProc.class.getName());
	
	public TestSuiteProc(String datasheet) {
		new ReadDatasheet(datasheet);
	}
	
	
	public Object[][] getDataProviderObject() throws Exception{

		LinkedHashMap<String, String> dataProvideMapInfo = ReadDatasheet.datamap;
		int  counter = 0;
		Object[][] dataProvider = new Object[dataProvideMapInfo.size()][2];

		for (String testdata : dataProvideMapInfo.keySet()) {
			dataProvider[counter][0] = testdata; // test script Name
			dataProvider[counter][1] = dataProvideMapInfo.get(testdata); // test script absolute path
			counter++;
		}
		if(counter == 0){
			System.out.println("ERROR :  No Test data found / No Test data is found from Testdata.xlsx.");
			logger.error("ERROR :  No Test data found / No Test data is found from Testdata.xlsx.");
			throw new Exception("ERROR :  No Test data found / No Test data is found from Testdata.xlsx.");
		}
		return dataProvider;

	}
}
