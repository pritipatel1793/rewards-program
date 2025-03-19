package com.rewards.config;

import com.rewards.model.Customer;
import com.rewards.model.Transaction;
import com.rewards.repository.CustomerRepository;
import com.rewards.repository.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Configuration class for initializing test data.
 */
@Configuration
public class DataInitializer {
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public DataInitializer(CustomerRepository customerRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }
    
    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Create customers
            Customer customer1 = new Customer();
            customer1.setName("John Doe");
            customer1.setEmail("john@example.com");
            customerRepository.save(customer1);
            
            Customer customer2 = new Customer();
            customer2.setName("Jane Smith");
            customer2.setEmail("jane@example.com");
            customerRepository.save(customer2);
            
            // Create transactions for customer1
            Transaction t1 = new Transaction();
            t1.setCustomer(customer1);
            t1.setAmount(new BigDecimal("120.00"));
            t1.setTimestamp(LocalDateTime.now().minusMonths(1));
            
            Transaction t2 = new Transaction();
            t2.setCustomer(customer1);
            t2.setAmount(new BigDecimal("75.00"));
            t2.setTimestamp(LocalDateTime.now().minusMonths(2));
            
            Transaction t3 = new Transaction();
            t3.setCustomer(customer1);
            t3.setAmount(new BigDecimal("200.00"));
            t3.setTimestamp(LocalDateTime.now().minusMonths(3));
            
            // Create transactions for customer2
            Transaction t4 = new Transaction();
            t4.setCustomer(customer2);
            t4.setAmount(new BigDecimal("150.00"));
            t4.setTimestamp(LocalDateTime.now().minusMonths(1));
            
            Transaction t5 = new Transaction();
            t5.setCustomer(customer2);
            t5.setAmount(new BigDecimal("60.00"));
            t5.setTimestamp(LocalDateTime.now().minusMonths(2));
            
            // Save all transactions
            transactionRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));
        };
    }
} 