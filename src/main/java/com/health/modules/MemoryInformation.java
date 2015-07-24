package com.health.modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

public class MemoryInformation {
	
	public JSONObject getMemoryInformation(){
		JSONObject memoryInfo=new JSONObject();
		Runtime rt = Runtime.getRuntime();
		try {
			Process proc = rt.exec("vmstat -s");
			InputStream stdin = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);

			String line = null;
			while ( (line = br.readLine()) != null){
			  
				
			   if(line.contains(" K ")){
			    	String []out=line.split(" K " );
			    	
			    	if(out[1].equals("total memory")){
			    		memoryInfo.put("total_memory", Long.valueOf(out[0].trim()));
			    	}
			    	
			    	if(out[1].equals("free memory")){
			    		memoryInfo.put("free", Long.valueOf(out[0].trim()));
			    	}
			    	
			    	if(out[1].equals("used memory")){
			    		memoryInfo.put("used", Long.valueOf(out[0].trim()));
			    	}
			    	
			    	if(out[1].equals("total swap")){
			    		memoryInfo.put("swap_total", Long.valueOf(out[0].trim()));
			    	}
			    	
			    	if(out[1].equals("used swap")){
			    		memoryInfo.put("swap_used", Long.valueOf(out[0].trim()));
			    	}
			    	
			    	if(out[1].equals("free swap")){
			    		memoryInfo.put("swap_free", Long.valueOf(out[0].trim()));
			    	}
			    }
			    
			   
				
			}

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memoryInfo;
	}

}
