package com.mystock.mygestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MygestockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MygestockApplication.class, args);
	}

}
