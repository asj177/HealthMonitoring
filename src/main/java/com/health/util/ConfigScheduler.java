package com.health.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class ConfigScheduler {
	
	public static  int timer=-1;
	public static ArrayList<String> processname=new ArrayList<String>();
	public static String password=null;
	
	public static ConfigScheduler config;
	
	public static ConfigScheduler getInstance(){
		config=new ConfigScheduler();
		return config;
	}
	public  void  setTimer(){
        Properties properties=new Properties();
		
		String propertyFileName="application.properties";
		
		try{
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
		if (inputStream != null) {
			
				properties.load(inputStream);
			
		} 
		
		if(timer==-1){
			//timer=Integer.getInteger(properties.getProperty("timerInfo"));
			String timerInfo=properties.getProperty("timeInfo");
			timer=Integer.parseInt(timerInfo);
		}
		if(processname.size()==0){
			String processes=properties.getProperty("processname");
			if(processes.contains(",")){
				
				
				String []process=processes.split(",");
				
				for(int i=0;i<process.length;i++){
					processname.add(process[i]);
				}
			}
			
			if(!processes.isEmpty()){
				processname.add(processes);
			}
			
		}
		
	
		if(password==null){
			password=properties.getProperty("password");
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
