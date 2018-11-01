package com.friday.test.Fridayskillassesment;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.friday.test.Fridayskillassesment.processor.Dataparser;
import com.friday.test.Fridayskillassesment.processor.TestSuiteProc;
import com.google.gson.JsonObject;




/**
 * Unit test for simple App.
 */
public class convertstringtoJSON_Negative extends Dataparser{
	
	
	private static Logger logger = Logger.getLogger(TestSuiteProc.class.getName());
	public LinkedHashMap<String, JsonObject> resultmap = new LinkedHashMap<String, JsonObject>() ;
	
	
	
	@Test(dataProvider="testScripts" , timeOut = 90000)
	public void TC_05_negative(String sno, String testdata) throws FileNotFoundException{
		logger.info("Test data "+testdata);
		splitdata_negative(testdata);
		jsonparser(resultmap);
	}
	
	@Test(dataProvider="testScripts" , timeOut = 90000)
	public void TC_06_negative(String sno, String testdata) throws FileNotFoundException{
		logger.info("Test data "+testdata);
		splitdata_negative(testdata);
		jsonparser(resultmap);
	}
	
	
	
	@DataProvider (parallel = false)
	public Object[][] testScripts(Method m) throws Exception {
		System.out.println(m.getName());
		TestSuiteProc testsuite = new TestSuiteProc(m.getName());
		return testsuite.getDataProviderObject();
	}
	
}
