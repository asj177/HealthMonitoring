package demo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.health.modules.CPU;
import com.health.modules.DeviceInformation;
import com.health.modules.MemoryInformation;
import com.health.modules.NetworkInfo;
import com.health.modules.ProcessInfo;
import com.health.modules.UploadData;
import com.health.util.ConfigScheduler;
import com.health.util.Constants;


@Component
public class Scheduler extends Thread implements Runnable{

	//public static final int timer=Constants.timer;
	
	@Scheduled(fixedRate=1000)
	public void healthMaintainance(){
		
		HashMap jsonOrderedMap = new HashMap();
		
		
		
		
		if(DeviceInformation.applianceId==null && DeviceInformation.motherboardNameInfo==null && DeviceInformation.serialNumberInfo==null){
			DeviceInformation deviceinfo=new DeviceInformation();
			deviceinfo.getDeviceInfo();
		}
		Date date=new Date(System.currentTimeMillis());
		Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   
			
		jsonOrderedMap.put("appliance_id_string", DeviceInformation.applianceId);
		jsonOrderedMap.put("mother_board_name", DeviceInformation.motherboardNameInfo);
		jsonOrderedMap.put("mother_board_serial_number", DeviceInformation.serialNumberInfo);
		jsonOrderedMap.put("@timestampp", format.format(date));
	    CPU cpu=new CPU();
		MemoryInformation memory=new MemoryInformation();
		ProcessInfo process=new ProcessInfo();
		NetworkInfo net=new NetworkInfo();
		
		jsonOrderedMap.put("cpu_stat_s", cpu.getCpuInformation());
		jsonOrderedMap.put("process_stat_s", process.getProcessInfo(Constants.processName));
		jsonOrderedMap.put("ether_stat_s", net.getNetworkInfo(Constants.networkPorts));
		JSONObject appliance_health=new JSONObject(jsonOrderedMap);
		
	    appliance_health.put("appliance_id_string", DeviceInformation.applianceId);
		appliance_health.put("mother_board_name", DeviceInformation.motherboardNameInfo);
		appliance_health.put("mother_board_serial_number", DeviceInformation.serialNumberInfo);
		appliance_health.put("@timestampp", format.format(date));
		appliance_health.put("cpu_stat_s", cpu.getCpuInformation());
		appliance_health.put("memory_stat_s", memory.getMemoryInformation());
		UploadData upload=new UploadData();
		upload.uploadData(jsonOrderedMap);
		System.out.println(appliance_health);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("*****************************Started************************************************************");
		while(true){
			
			
			try {
				healthMaintainance();
				Thread.sleep(ConfigScheduler.timer);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
