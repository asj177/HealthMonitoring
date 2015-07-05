package demo;

import java.util.Arrays;
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
import com.health.util.ConfigScheduler;
import com.health.util.Constants;

@Component
public class Scheduler extends Thread implements Runnable{

	//public static final int timer=Constants.timer;
	
	@Scheduled(fixedRate=1000)
	public void healthMaintainance(){
		
		LinkedHashMap jsonOrderedMap = new LinkedHashMap();
		
		
		
		
		if(DeviceInformation.applianceId==null && DeviceInformation.motherboardNameInfo==null && DeviceInformation.serialNumberInfo==null){
			DeviceInformation deviceinfo=new DeviceInformation();
			deviceinfo.getDeviceInfo();
		}
		
		jsonOrderedMap.put("appliance_id_string", DeviceInformation.applianceId);
		jsonOrderedMap.put("mother_board_name", DeviceInformation.motherboardNameInfo);
		jsonOrderedMap.put("mother_board_serial_number", DeviceInformation.serialNumberInfo);
		
		CPU cpu=new CPU();
		MemoryInformation memory=new MemoryInformation();
		ProcessInfo process=new ProcessInfo();
		NetworkInfo net=new NetworkInfo();
		
		jsonOrderedMap.put("cpu_stat_s", cpu.getCpuInformation());
		jsonOrderedMap.put("memory_stat_s", memory.getMemoryInformation());
		jsonOrderedMap.put("process_stat_s", process.getProcessInfo(Constants.processName));
		jsonOrderedMap.put("ether_stat_s", net.getNetworkInfo(Constants.networkPorts));
		JSONObject appliance_health=new JSONObject(jsonOrderedMap);
		
		/*appliance_health.put("appliance_id_string", DeviceInformation.applianceId);
		appliance_health.put("mother_board_name", DeviceInformation.motherboardNameInfo);
		appliance_health.put("mother_board_serial_number", DeviceInformation.serialNumberInfo);
		
		appliance_health.put("cpu_stat_s", cpu.getCpuInformation());
		appliance_health.put("memory_stat_s", memory.getMemoryInformation());*/
		
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
