package com.bearl.springbootpetfoodtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootPetfoodTrackerApplication {

	/*
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootPetfoodTrackerApplication.class, args);
	}

}
