package com.rewards.repository;

import com.rewards.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Transaction entity.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    /**
     * Finds all transactions for a customer within a specified time period.
     *
     * @param customerId The ID of the customer
     * @param timestamp The start timestamp for the search
     * @return List of transactions matching the criteria
     */
    List<Transaction> findByCustomerIdAndTimestampGreaterThanEqual(Long customerId, LocalDateTime timestamp);
} 