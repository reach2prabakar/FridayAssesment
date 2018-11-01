package com.friday.test.Fridayskillassesment.processor;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.friday.test.Fridayskillassesment.library.Inputenum;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class Dataparser {

	private static Logger logger = Logger.getLogger(Dataparser.class.getName());
	private String housenumber;
	private String street;
	String housenum;
	String strt;
	int i=1;
	
	public void splitdata(String address){
		 housenum = getMatch(Pattern.compile("((^\\d+)|(^No \\d+)|(^# \\d+))|((No \\d+$)|(# \\d+$)|( \\d+[\\s]?[\\w]?$))"), address);
		 strt = address.replaceAll(housenum, "").trim().replaceAll("^,|,$", "");
		
		String localvar = getMatch(Pattern.compile("\\d+"), housenum);
		logger.info("the housenumber after compiling :"+localvar);
		if(localvar.isEmpty()||localvar==null){
			System.out.println("house number should have numeric values");
			logger.error("House number is "+address+" - house number should have numeric values");
			assertTrue(false,"House number is "+address+" - house number should have numeric values");
			
		}
		localvar = strt.replaceAll("(^\\d+)|(\\d+$)", "").trim();
		logger.info("the street name after compiling :"+localvar);
		if(!localvar.matches("\\D+")){
			System.out.println("Street name should have string values");
			logger.error("Street name is "+strt+" - Street name should have string values with no numbers between");
			assertTrue(false,"Street name is "+strt+" - Street name should have string values with no numbers between");
		}
		sethousenumber(housenum);
		setstreet(strt);
	}
	
	public void splitdata_negative(String address){
		 housenum = getMatch(Pattern.compile("((^\\d+)|(^No \\d+)|(^# \\d+))|((No \\d+$)|(# \\d+$)|( \\d+[\\s]?[\\w]?$))"), address);
		 strt = address.replaceAll(housenum, "").trim().replaceAll("^,|,$", "");
		
		String localvar = getMatch(Pattern.compile("\\d+"), housenum);
		logger.info("the housenumber after compiling :"+localvar);
		if(localvar.isEmpty()||localvar==null){
			System.out.println("house number should have numeric values");
			logger.error("House number is "+address+" - house number should have numeric values");
			assertFalse(false,"House number is "+address+" - house number should have numeric values");
			
		}
		localvar = strt.replaceAll("(^\\d+)|(\\d+$)", "").trim();
		logger.info("the street name after compiling :"+localvar);
		if(!localvar.matches("\\D+")){
			System.out.println("Street name should have string values");
			logger.error("Street name is "+strt+" - Street name should have string values with no numbers between");
			assertFalse(false,"Street name is "+strt+" - Street name should have string values with no numbers between");
		}
		sethousenumber(housenum);
		setstreet(strt);
	}
	
	public String getMatch(Pattern pattern, String inputString) {
		Matcher regexMatcher = pattern.matcher(inputString);
		return regexMatcher.find() ? regexMatcher.group().trim() : "";
	} 
	
	
	public void jsonparser(LinkedHashMap<String, JsonObject> resultmap) throws FileNotFoundException{
		
		Inputenum key = createdataobject();
		Gson gson = new Gson();
		String json = gson.toJson(key);
		resultmap.put("data"+i, gson.toJsonTree(key).getAsJsonObject());
		logger.info("the json parser output is "+ json);
		System.out.println(json);
		i++;
     }
	
	public void writejsontoFile(LinkedHashMap<String, JsonObject> resultmap){
		PrintWriter pw =null;
		try {
			pw = new PrintWriter("JSONoutput.json");
		} catch (FileNotFoundException e) {
			logger.error("the print writer job is failed , Check the Jsonoutput file is created?");
			e.printStackTrace();
		}
		Set<String> keys = resultmap.keySet();
		for (String Resultkey : keys) {
			pw.write(resultmap.get(Resultkey).toString().trim());
			logger.info("the result json is "+resultmap.get(Resultkey).toString().trim());
		}
		pw.flush(); 
        pw.close();
	}
	
	private Inputenum createdataobject() {

		Inputenum input = new Inputenum();
		input.setStreet(strt);
		input.setHousenumber(housenumber);

        return input;

    }
	
	
	public String gethousenumber() {
		return housenumber;
	}
	public void sethousenumber(String house) {
		this.housenumber = house;
	}
	public String getstreet() {
		return street;
	}
	public void setstreet(String add) {
		this.street = add;
	}
	
	
}
