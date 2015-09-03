package com.health.modules;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;



public class CPU {
	
	public JSONObject getCpuInformation(){
		JSONObject cpuinfo=new JSONObject();
		Runtime rt = Runtime.getRuntime();
		
		try{
			Process proc = rt.exec("acpi -t");
			InputStream stdin = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);

			String line = null;
			
			while ( (line = br.readLine()) != null){
			    String []out=line.split(" ");
			     cpuinfo.put("temp_in_celsius", Double.valueOf(out[3].trim()));
			}
			proc=rt.exec("cat /proc/cpuinfo");
			InputStream stdin2 = proc.getInputStream();
			InputStreamReader isr2 = new InputStreamReader(stdin2);
			BufferedReader br2 = new BufferedReader(isr2);
			String cpuMainInfo=null;
			while ( (cpuMainInfo = br2.readLine()) != null){
				 String []output=cpuMainInfo.split(":");
				    if(output[0].trim().equals("model name")) {
				    	cpuinfo.put("model_name", output[1].trim());
				    	
				    }
				    
				    if(output[0].trim().equals("cpu MHz")) {
				    	cpuinfo.put("cpu_MHz_String", output[1].trim());
				    }
				    
				    if(output[0].trim().equals("cache size")) {
				    	cpuinfo.put("cache_size_string", output[1].trim());
				    }
					
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return cpuinfo;
	}

}
