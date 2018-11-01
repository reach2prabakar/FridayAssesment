package com.friday.test.Fridayskillassesment;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.friday.test.Fridayskillassesment.processor.Dataparser;
import com.friday.test.Fridayskillassesment.processor.TestSuiteProc;
import com.google.gson.JsonObject;




/**
 * Unit test for simple App.
 */
public class convertstringtoJSON extends Dataparser{
	
	
	private static Logger logger = Logger.getLogger(TestSuiteProc.class.getName());
	public LinkedHashMap<String, JsonObject> resultmap = new LinkedHashMap<String, JsonObject>() ;
	
	@Parameters({"AutomationEnvironment", "EmailResipents"})
	@BeforeSuite
	public void intializeSuite(@Optional("") String environment,@Optional("") String emailResipents) throws Exception{
	
		logger.info("environment from jenkins"+environment);
	}
	
	
	@Test(dataProvider="testScripts" , timeOut = 1800000)
	public void TC_02(String sno, String testdata) throws FileNotFoundException{
		logger.info("Test data "+testdata);
		splitdata(testdata);
		jsonparser(resultmap);
	}
	
	@Test(dataProvider="testScripts" , timeOut = 90000)
	public void TC_03(String sno, String testdata) throws FileNotFoundException{
		logger.info("Test data "+testdata);
		splitdata(testdata);
		jsonparser(resultmap);
	}
	
	@Test(dataProvider="testScripts" , timeOut = 90000)
	public void TC_04(String sno, String testdata) throws FileNotFoundException{
		logger.info("Test data "+testdata);
		splitdata(testdata);
		jsonparser(resultmap);
	}
	
	
	@AfterTest
	public void generateJsonfile(){
		writejsontoFile(resultmap);
	}
	
	@DataProvider (parallel = false)
	public Object[][] testScripts(Method m) throws Exception {
		System.out.println(m.getName());
		TestSuiteProc testsuite = new TestSuiteProc(m.getName());
		return testsuite.getDataProviderObject();
	}
	
}
