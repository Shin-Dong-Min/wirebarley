package com.wirebarley.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.wirebarley.api.ExchangeApiController;

@SpringBootApplication
@ComponentScan(basePackageClasses = ExchangeApiController.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
