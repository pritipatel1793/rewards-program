package com.rewards.controller;

import com.rewards.model.Customer;
import com.rewards.repository.CustomerRepository;
import com.rewards.service.RewardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for handling customer and rewards-related endpoints.
 */
@RestController
@RequestMapping("/api")
public class CustomerRewardsController {
    private final CustomerRepository customerRepository;
    private final RewardsService rewardsService;

    public CustomerRewardsController(CustomerRepository customerRepository, RewardsService rewardsService) {
        this.customerRepository = customerRepository;
        this.rewardsService = rewardsService;
    }

    /**
     * Retrieves all customers.
     *
     * @return ResponseEntity containing the list of all customers
     */
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    /**
     * Calculates monthly reward points for a specific customer.
     *
     * @param customerId The ID of the customer
     * @return ResponseEntity containing the customer's monthly reward points
     */
    @GetMapping("/rewards/customer/{customerId}/monthly")
    public ResponseEntity<Map<String, Object>> getCustomerMonthlyRewardPoints(@PathVariable Long customerId) {
        Map<String, Object> monthlyPoints = rewardsService.calculateMonthlyRewardPoints(customerId);
        return ResponseEntity.ok(monthlyPoints);
    }

    /**
     * Calculates total reward points for a specific customer.
     *
     * @param customerId The ID of the customer
     * @return ResponseEntity containing the customer's total reward points
     */
    @GetMapping("/rewards/customer/{customerId}/total")
    public ResponseEntity<Map<String, Object>> getCustomerTotalRewardPoints(@PathVariable Long customerId) {
        Map<String, Object> totalPoints = rewardsService.calculateTotalRewardPoints(customerId);
        return ResponseEntity.ok(totalPoints);
    }
} 