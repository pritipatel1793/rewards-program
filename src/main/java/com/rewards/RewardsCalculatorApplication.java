package com.rewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Rewards Calculator.
 * This Spring Boot application provides RESTful APIs for calculating
 * reward points based on customer transactions.
 */
@SpringBootApplication
public class RewardsCalculatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(RewardsCalculatorApplication.class, args);
    }
} 