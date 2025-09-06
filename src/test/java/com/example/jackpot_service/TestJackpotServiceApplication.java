package com.example.jackpot_service;

import org.springframework.boot.SpringApplication;

public class TestJackpotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(JackpotServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
