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
	public static ArrayList<String> ports=new ArrayList<String>();
	
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
			String timerInfo=properties.getProperty("polling_interval");
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
		
		if(ports.size()==0){
			String portsInfo=properties.getProperty("network_interface_seed_string");
			String inteface=properties.getProperty("interface_range");
		    int interfacerange=Integer.parseInt(inteface);
			
			if(!portsInfo.isEmpty() || portsInfo!=null){
			 if(portsInfo.contains(",")){
				 String []split=portsInfo.split(",");
				 for(int i=0;i<split.length;i++){
					 for(int j=0;i<interfacerange;j++){
						 String portsInfor=split[i]+j;
						 ports.add(portsInfor);
					 }
					 
					 
				 }
				 
			 }else{
				 for(int i=0;i<=interfacerange;i++){
					 String portsInfor=portsInfo+i;
					 ports.add(portsInfor);
				 }
				 
			 }
			 
			 
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
