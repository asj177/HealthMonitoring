package com.health.info;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import com.health.util.Constants;

public class DeviceInformation {

   public static String applianceId=null;
   public static String motherboardNameInfo=null;
   public static String serialNumberInfo=null;
	
	public void getDeviceInfo(){
		
		
		
		try{
			
			String command="echo "+Constants.password +"| sudo -S dmidecode";
			String []cmd={"/bin/sh","-c",command};
			
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream stdin = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);
			
			String line = null;
			
			boolean applicanceId=false;
			boolean motherboardname=false;
			boolean serialNumber=false;
			while ( (line = br.readLine()) != null){
				
				if(line.contains("System Information")){
					while(!applicanceId || !motherboardname || !serialNumber ){
						line=br.readLine();
						String []output=line.split(":");
						
						if(line.contains("Serial")){
							serialNumberInfo=output[1];
							
							serialNumber=true;
						}
						
						if(line.contains("UUID")){
							applianceId=output[1];
							
							applicanceId=true;
						}
						
						if(line.contains("Product")){
							motherboardNameInfo=output[1];
							
							motherboardname=true;
						}
					}
					
					
				}
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
