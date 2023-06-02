package com.dbd.seoulcinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SeoulcinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeoulcinemaApplication.class, args);
	}

}
