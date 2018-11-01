/*
 * Decompiled with CFR 0_114.
 * 
 * Could not load the following classes:
 *  org.openqa.selenium.WebDriver
 *  org.testng.IClass
 *  org.testng.IInvokedMethod
 *  org.testng.IResultMap
 *  org.testng.IRetryAnalyzer
 *  org.testng.ISuite
 *  org.testng.ISuiteListener
 *  org.testng.ITestContext
 *  org.testng.ITestListener
 *  org.testng.ITestNGMethod
 *  org.testng.ITestResult
 *  org.testng.Reporter
 *  org.testng.internal.ConstructorOrMethod
 *  org.testng.internal.Utils
 *  org.testng.xml.XmlTest
 */
package com.friday.test.Fridayskillassesment.library;


import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.friday.test.Fridayskillassesment.processor.TestSuiteProc;



public class WebTestListeners implements ITestListener, ISuiteListener {
    public String sFilename;
    private static String screenshotPath;
    private static Logger logger = Logger.getLogger(WebTestListeners.class.getName());
    
    public static String getScreenshotPath() {
        return screenshotPath;
    }
 
    public void onFinish(ITestContext context) {
    	System.out.println();
    	System.out.println("................. Suite executed and completed.......................");
    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    	System.out.println();
        System.out.println("LOG file for this suite can be found in "+System.getProperty("user.dir")+"/Logs/application/log");
        System.out.println();
        System.out.println();
        System.out.println("JSONoutput.json File will be created and the output is written in the file "+System.getProperty("user.dir")+"/JSONoutput.json");
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    	
    }

    
    public void onStart(ISuite arg0) {
    	
    	System.out.println("................. Suite  --- on start .......................");
        System.out.println();
    }
    public void onTestStart(ITestResult iTestResult){
    	logger.info(iTestResult.getName() + "Started execution");
    //	System.out.println("Test has beed started");
    }
    
    @Override
	public void onStart(ITestContext context) {
		
    	
		// TODO Auto-generated method stub
		
		//System.out.println(context.getAllTestMethods().toString());
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info(result.getName() + "completed execution with pass result");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info(result.getName() + "completed execution with Fail result");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info(result.getName() + "skipped execution");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
}

