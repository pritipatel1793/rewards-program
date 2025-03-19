package com.rewards.repository;

import com.rewards.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Customer entity.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
} 