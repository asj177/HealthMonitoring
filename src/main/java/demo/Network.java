package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.health.info.CPU;
import com.health.info.ProcessInfo;

public class Network {

	public static void main(String[] args) {
		
		try{
		String processLine=null;
		String processName="java";
		String []cmd={"/bin/sh","-c","echo Saibaba@177 | sudo -S dmidecode"};
		Process p = Runtime.getRuntime().exec(cmd);
		InputStream stdin = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(stdin);
		BufferedReader br = new BufferedReader(isr);

		String line = null;
		System.out.println("<OUTPUT>");

		int i=0;
		boolean applicanceId=false;
		boolean motherboardname=false;
		boolean serialNumber=false;
		while ( (line = br.readLine()) != null){
			
			if(line.contains("System Information")){
				while(!applicanceId || !motherboardname || !serialNumber ){
					line=br.readLine();
					String []output=line.split(":");
					if(line.contains("Serial")){
						System.out.println("serial number "+output[1]);
						serialNumber=true;
					}
					
					if(line.contains("UUID")){
						System.out.println("UUID "+output[1]);
						applicanceId=true;
					}
					
					if(line.contains("Product")){
						System.out.println("Product "+output[1]);
						motherboardname=true;
					}
				}
				
				
			}
		 // System.out.println(line);
			
		   
		    
		   
			
		}

		System.out.println("</OUTPUT>");

		
		System.out.println(processLine);

		}catch(Exception e){
			e.printStackTrace();
		}
		/*ProcessInfo cp=new ProcessInfo();
		
		cp.getProcessInfo("java");*/
		/*Runtime rt = Runtime.getRuntime();
		try {
			Process proc = rt.exec("vmstat -s");
			InputStream stdin = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);

			String line = null;
			System.out.println("<OUTPUT>");

			int i=0;
			
			while ( (line = br.readLine()) != null){
			  //System.out.println(line);
				
			   if(line.contains(" K ")){
			    	String []out=line.split(" K " );
			    	System.out.println(out[1]+"  "+out[0]);
			    	
			    }
			    
			   
				
			}

			System.out.println("</OUTPUT>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
