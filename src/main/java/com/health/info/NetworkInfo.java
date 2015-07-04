package com.health.info;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class NetworkInfo {

	
	public JSONArray getNetworkInfo(ArrayList<String>ports){

		JSONArray net=new JSONArray();	
		
		if(ports!=null || ports.size()!=0){
			
			for(String portNumber:ports){
		try{
		
		JSONObject info=new JSONObject();	
		
		String cmd="ifconfig -a "+portNumber;
		info.put("port_name",portNumber);
		Process p = Runtime.getRuntime().exec(cmd);
		InputStream stdin = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(stdin);
		BufferedReader br = new BufferedReader(isr);

		String line = null;
		
		
		int k=0;
				   
		line=br.readLine();
		
		if(line.contains("HWaddr")){
			String []split=line.split("\\s+");
			info.put("mac_address", split[4]);
		}
		
		p= Runtime.getRuntime().exec("ethtool  eth0");
		InputStream stdin1 = p.getInputStream();
		InputStreamReader isr1 = new InputStreamReader(stdin1);
		BufferedReader br1 = new BufferedReader(isr1);

		String line1 = null;
	
		while((line1=br1.readLine())!=null){
			line1.trim();
			if(line1.contains("Speed")){info.put("uint8_t link_state", 0 );
				String []sp=line1.split(":");
				info.put("speed", sp[1].trim());
			}
			
			
			if(line1.contains("Link detected")){
				String []sp=line1.split(":");
				
				if(sp[1].equals("no")){
					info.put("uint8_t link_state", 0);
				}else{
					info.put("uint8_t link_state", 1);
				}
				
			}
		}
			
		
		net.put(info);
		
		//System.out.println(net);
	

		}catch(Exception e){
			e.printStackTrace();
		}
		
		}
		}
		
		return net;
	}
	
	
}
