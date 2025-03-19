package com.rewards.service;

import com.rewards.model.Customer;
import com.rewards.model.Transaction;
import com.rewards.repository.CustomerRepository;
import com.rewards.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for calculating reward points based on customer transactions.
 */
@Service
public class RewardsService {
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public RewardsService(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    public Map<String, Object> calculateRewardPoints(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndTimestampGreaterThanEqual(customerId, threeMonthsAgo);
        
        Map<String, Object> response = new HashMap<>();
        Map<Month, Integer> monthlyPoints = new HashMap<>();
        int totalPoints = 0;

        for (Transaction transaction : transactions) {
            int points = calculatePointsForTransaction(transaction.getAmount());
            Month month = transaction.getTimestamp().getMonth();
            monthlyPoints.merge(month, points, Integer::sum);
            totalPoints += points;
        }

        response.put("customerId", customer.getId());
        response.put("customerName", customer.getName());
        response.put("monthlyPoints", monthlyPoints);
        response.put("totalPoints", totalPoints);

        return response;
    }

    private int calculatePointsForTransaction(BigDecimal amount) {
        int points = 0;
        double value = amount.doubleValue();

        if (value > 100) {
            points += 2 * (value - 100);
        }
        if (value > 50) {
            points += Math.min(50, value - 50);
        }

        return points;
    }
} 