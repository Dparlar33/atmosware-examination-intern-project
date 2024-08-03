package com.atmosware.managementService;

import com.atmosware.core.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableSecurity
@EnableFeignClients
public class ManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementServiceApplication.class, args);
	}

}
