package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

import com.health.modules.CPU;
import com.health.modules.ProcessInfo;

public class Network {

	/*public static void main(String[] args) {
		
		
		
		JSONArray net=new JSONArray();	
		try{
		
		JSONObject info=new JSONObject();	
		
		String cmd="ifconfig -a eth0";
		info.put("port_name","eth0");
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
		
		System.out.println(net);
	

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}*/
}
