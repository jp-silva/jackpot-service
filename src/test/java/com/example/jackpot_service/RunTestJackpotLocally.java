package com.example.jackpot_service;

import org.springframework.boot.SpringApplication;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

public class RunTestJackpotLocally {
  public static void main(String[] args) {
    System.setProperty(ACTIVE_PROFILES_PROPERTY_NAME, "local");
    SpringApplication.from(JackpotServiceApplication::main)
        .with(TestcontainersConfiguration.class)
        .run(args);
  }
}
