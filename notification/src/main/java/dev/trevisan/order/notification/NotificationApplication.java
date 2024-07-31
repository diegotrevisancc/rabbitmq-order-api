package dev.trevisan.order.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationApplication {
	// will run without tomcat
	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

}
