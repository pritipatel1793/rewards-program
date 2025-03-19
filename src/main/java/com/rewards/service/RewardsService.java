package com.rewards.service;

import com.rewards.exception.CustomerNotFoundException;
import com.rewards.exception.InvalidInputException;
import com.rewards.model.Customer;
import com.rewards.model.Transaction;
import com.rewards.repository.CustomerRepository;
import com.rewards.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * Calculates monthly reward points for a customer.
     *
     * @param customerId The ID of the customer
     * @return Map containing customer details and monthly points
     * @throws CustomerNotFoundException if the customer is not found
     * @throws InvalidInputException if the customerId is null or negative
     */
    public Map<String, Object> calculateMonthlyRewardPoints(Long customerId) {
        validateCustomerId(customerId);
        
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndTimestampGreaterThanEqual(customerId, threeMonthsAgo);
        
        Map<String, Object> response = new HashMap<>();
        Map<String, Integer> monthlyPoints = new HashMap<>();

        // Group transactions by month and calculate points
        transactions.stream()
            .collect(Collectors.groupingBy(
                t -> YearMonth.from(t.getTimestamp()),
                Collectors.summingInt(t -> calculatePointsForTransaction(t.getAmount()))
            ))
            .forEach((yearMonth, points) -> 
                monthlyPoints.put(yearMonth.toString(), points)
            );

        response.put("customerId", customer.getId());
        response.put("customerName", customer.getName());
        response.put("monthlyPoints", monthlyPoints);

        return response;
    }

    /**
     * Calculates total reward points for a customer.
     *
     * @param customerId The ID of the customer
     * @return Map containing customer details and total points
     * @throws CustomerNotFoundException if the customer is not found
     * @throws InvalidInputException if the customerId is null or negative
     */
    public Map<String, Object> calculateTotalRewardPoints(Long customerId) {
        validateCustomerId(customerId);
        
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndTimestampGreaterThanEqual(customerId, threeMonthsAgo);
        
        Map<String, Object> response = new HashMap<>();
        int totalPoints = transactions.stream()
            .mapToInt(t -> calculatePointsForTransaction(t.getAmount()))
            .sum();

        response.put("customerId", customer.getId());
        response.put("customerName", customer.getName());
        response.put("totalPoints", totalPoints);

        return response;
    }

    private void validateCustomerId(Long customerId) {
        if (customerId == null) {
            throw new InvalidInputException("Customer ID cannot be null");
        }
        if (customerId <= 0) {
            throw new InvalidInputException("Customer ID must be positive");
        }
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