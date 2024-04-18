package com.manicuria.citasservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CitasServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasServicioApplication.class, args);
	}

}
