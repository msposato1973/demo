package com.fsb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DemoFsbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFsbApplication.class, args);
	}

}
