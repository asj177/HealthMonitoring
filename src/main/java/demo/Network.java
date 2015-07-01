package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

import com.health.info.CPU;
import com.health.info.ProcessInfo;

public class Network {

	public static void main(String[] args) {
		
		try{
		String processLine=null;
		String processName="java";
		
		String middle="\"%-8s %-8s %-8s %-8s \\n \"";
		
		String first=" | grep \"^ \"";
		String cc="top -bn 1"+first +"|  awk '{ printf("+
				
middle
				
				+", $1,$9, $10, $12); }' | head -n 11";
		
		
		String []cmd={"/bin/sh","-c",cc};
		Process p = Runtime.getRuntime().exec(cmd);
		InputStream stdin = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(stdin);
		BufferedReader br = new BufferedReader(isr);

		String line = null;
		
		 // System.out.println(line);
		int k=0;
		/*do{
			line = br.readLine();
			k++;
		}while(k<7);*/		   
		    
		   
			
		while ( (line = br.readLine()) != null){
			
			//System.out.println(line);
			
			//System.out.println(line.trim());
			String line2=line.substring(1, line.length()-1);
			
			String []splie=line2.split("    ");
			
			for(int i=0;i<splie.length;i++){
			System.out.print(splie[i]+"  ");
			}
			
			System.out.println(" ");
			/*String []splitLine=line.split(" ");
			System.out.print("process_name   "+splitLine[splitLine.length-1].trim());
			
			System.out.print("  ");
			
			System.out.print("process_pid    "+splitLine[0]);
			
			System.out.print("  ");
			
			System.out.print("uint64_t mem_in_use  "+splitLine[19]);
			
			System.out.println(" ");*/
			/*if(line.contains(name)){
				JSONObject processInfo=new JSONObject();
				String []splitLine=line.split(" ");
				
				
				processInfo.put("process_name", name);
				processInfo.put("process_pid", splitLine[1].trim());
				processInfo.put("uint64_t mem_in_use", splitLine[19].trim());
				processArray.put(processInfo);
				
				i++;
			}*/
			
		}

	

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
