package com.manicuria.turnosservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class TurnosServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurnosServicioApplication.class, args);
	}

}
