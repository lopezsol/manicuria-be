package com.manicuria.serviciosservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiciosServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciosServicioApplication.class, args);
	}

}
