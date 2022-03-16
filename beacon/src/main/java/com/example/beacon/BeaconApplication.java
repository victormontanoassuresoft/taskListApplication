package com.example.beacon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BeaconApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeaconApplication.class, args);
	}

}
