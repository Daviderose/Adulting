package org.launchcode.Adulting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

// Main application
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class AdultingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdultingApplication.class, args);
	}
}
