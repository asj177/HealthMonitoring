package com.health.info;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProcessInfo {
	
	
	public JSONArray getProcessInfo(ArrayList<String> names){
		
		if(names.size()==0 || names==null){
			return getDefaultResults();
		}else{
			return getProcessWithName(names);
		}
	}
	
	
	private JSONArray getDefaultResults(){
		JSONArray processArray=new JSONArray();
		Runtime rt = Runtime.getRuntime();
		try{
			String middle="\"%-8s %-8s %-8s %-8s \\n \"";
			
			String first=" | grep \"^ \"";
			String cc="top -bn 1"+first +"|  awk '{ printf("+middle+", $1,$9, $10, $12); }' | head -n 11";
			
			
			String []cmd={"/bin/sh","-c",cc};
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream stdin = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);

			String line = null;
			
			
			line = br.readLine();
			while ( (line = br.readLine()) != null){
				
				String line2=line.substring(1, line.length()-1);
				String []splie=line2.split("\\s+");
				//[6136,  36.4,  24.4,  java]
				JSONObject processInfo=new JSONObject();
				processInfo.put("process_name", splie[3].trim());
				processInfo.put("process_pid", splie[0].trim());
				processInfo.put("uint64_t mem_in_use", splie[2].trim());
				processArray.put(processInfo);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return processArray;
	}
	
	
	private JSONArray getProcessWithName(ArrayList<String>names){
		JSONArray processArray=new JSONArray();
		Runtime rt = Runtime.getRuntime();
		try{
			
			for(String name:names){
			
            String middle="\"%-8s %-8s %-8s %-8s \\n \"";
			
			String first=" | grep \"^ \"";
			String cc="top -bn 1"+first +"|  awk '{ printf("+middle+", $1,$9, $10, $12); }' | grep "+name;
			
			
			String []cmd={"/bin/sh","-c",cc};
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream stdin = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);

			String line = null;
			
			
			line = br.readLine();
			
			
				while ( (line = br.readLine()) != null){
					
					
						
						String line2=line.substring(1, line.length()-1);
						String []splie=line2.split("\\s+");
						
						
						
						JSONObject processInfo=new JSONObject();
						
						
						
						processInfo.put("process_name", splie[0]);
						processInfo.put("process_pid", splie[1].trim());
						processInfo.put("uint64_t mem_in_use", splie[2].trim());
						processArray.put(processInfo);
						
						
					
					
				}
			}
			
		}catch(Exception e){
			
		}
		
		return processArray;
	}
	
	
	
	

}
