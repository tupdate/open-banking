package com.cts.open.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OpenAccountService 
{
	public static void main(String[] args) {
		SpringApplication.run(OpenAccountService.class, args);
	}
}
