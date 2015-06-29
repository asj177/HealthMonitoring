package com.health.info;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProcessInfo {
	
	
	public JSONArray getProcessInfo(String name){
		
		
		JSONArray processArray=new JSONArray();
		Runtime rt = Runtime.getRuntime();
		try{
			
			
			Process proc = rt.exec("top -b -n 1");
			
			InputStream stdin = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);

			String line = null;
			
			int i=0;
			while ( (line = br.readLine()) != null){
				
				if(line.contains(name)){
					JSONObject processInfo=new JSONObject();
					String []splitLine=line.split(" ");
					
					
					processInfo.put("process_name", name);
					processInfo.put("process_pid", splitLine[1].trim());
					processInfo.put("uint64_t mem_in_use", splitLine[19].trim());
					processArray.put(processInfo);
					
					i++;
				}
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return processArray;
	}
	
	

}
