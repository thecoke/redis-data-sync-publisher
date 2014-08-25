package demo;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

	
	@Autowired
	private StringRedisTemplate template;
	
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    


	@Override
	public void run(String... args) throws Exception {
		
		String key = "test.zset";
		long i = 1;
		while (true) {
			Thread.sleep(1000);
			
			Calendar cal = Calendar.getInstance();
			double time = (double) cal.getTimeInMillis();
			
			template.opsForZSet().add(key, String.format("%08d",i), time);
			
			System.out.println("Found key " + key + ", value=" + String.format("%08d",i) + ", score="+ time);
			i++;
		}
	}
}
