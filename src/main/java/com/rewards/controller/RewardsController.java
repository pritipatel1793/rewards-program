package com.rewards.controller;

import com.rewards.service.RewardsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST Controller for handling rewards-related endpoints.
 */
@RestController
@RequestMapping("/api/rewards")
public class RewardsController {
    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    /**
     * Calculates reward points for a specific customer.
     *
     * @param customerId The ID of the customer
     * @return ResponseEntity containing the customer's reward points
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Map<String, Object>> getCustomerRewardPoints(@PathVariable Long customerId) {
        Map<String, Object> rewardPoints = rewardsService.calculateRewardPoints(customerId);
        return ResponseEntity.ok(rewardPoints);
    }
} 