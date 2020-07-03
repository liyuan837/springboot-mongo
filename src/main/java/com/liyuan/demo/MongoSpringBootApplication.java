package com.liyuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class MongoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoSpringBootApplication.class, args);
	}
}