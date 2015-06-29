package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.health.util.ConfigScheduler;

@SpringBootApplication
@EnableScheduling
public class HealthMonitoringApplication {

	
    public static void main(String[] args) {
    	ConfigScheduler config=ConfigScheduler.getInstance();
    	config.setTimer();
    	Scheduler scheduler=new Scheduler();
    	scheduler.start();
        //SpringApplication.run(HealthMonitoringApplication.class, args);
    	
    }
}
