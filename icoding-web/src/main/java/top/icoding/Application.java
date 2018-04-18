package top.icoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
* @author 我是金角大王
* @date 2017年11月20日 下午3:00:04
*/
@SpringBootApplication
@EnableCaching
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
