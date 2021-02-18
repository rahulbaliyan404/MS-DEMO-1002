package com.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsDemo1002Application {
// This is MS-Demo-1002 application main method.
	public static void main(String[] args) {
		SpringApplication.run(MsDemo1002Application.class, args);
	}

}
