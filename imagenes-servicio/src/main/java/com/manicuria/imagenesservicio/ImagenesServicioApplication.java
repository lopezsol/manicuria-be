package com.manicuria.imagenesservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ImagenesServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImagenesServicioApplication.class, args);
	}

}
