package com.rewards.service;

import com.rewards.model.Customer;
import com.rewards.model.Transaction;
import com.rewards.repository.CustomerRepository;
import com.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RewardsServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    
    @Mock
    private TransactionRepository transactionRepository;
    
    @InjectMocks
    private RewardsService rewardsService;
    
    private Customer customer;
    private List<Transaction> transactions;
    
    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        
        Transaction t1 = new Transaction();
        t1.setCustomer(customer);
        t1.setAmount(new BigDecimal("120.00"));
        t1.setTimestamp(LocalDateTime.now().minusMonths(1));
        
        Transaction t2 = new Transaction();
        t2.setCustomer(customer);
        t2.setAmount(new BigDecimal("75.00"));
        t2.setTimestamp(LocalDateTime.now().minusMonths(2));
        
        transactions = Arrays.asList(t1, t2);
    }
    
    @Test
    void calculateMonthlyRewardPoints_WithValidCustomer_ReturnsCorrectPoints() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        when(transactionRepository.findByCustomerIdAndTimestampGreaterThanEqual(anyLong(), any()))
            .thenReturn(transactions);
        
        // Act
        Map<String, Object> result = rewardsService.calculateMonthlyRewardPoints(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.get("customerId"));
        assertEquals("John Doe", result.get("customerName"));
        assertFalse(((Map<?, ?>) result.get("monthlyPoints")).isEmpty());
    }
    
    @Test
    void calculateTotalRewardPoints_WithValidCustomer_ReturnsCorrectPoints() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        when(transactionRepository.findByCustomerIdAndTimestampGreaterThanEqual(anyLong(), any()))
            .thenReturn(transactions);
        
        // Act
        Map<String, Object> result = rewardsService.calculateTotalRewardPoints(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(1L, result.get("customerId"));
        assertEquals("John Doe", result.get("customerName"));
        assertTrue((Integer) result.get("totalPoints") > 0);
    }
    
    @Test
    void calculateMonthlyRewardPoints_WithInvalidCustomer_ThrowsException() {
        // Arrange
        when(customerRepository.findById(999L)).thenReturn(java.util.Optional.empty());
        
        // Act & Assert
        assertThrows(RuntimeException.class, () -> rewardsService.calculateMonthlyRewardPoints(999L));
    }
    
    @Test
    void calculateTotalRewardPoints_WithInvalidCustomer_ThrowsException() {
        // Arrange
        when(customerRepository.findById(999L)).thenReturn(java.util.Optional.empty());
        
        // Act & Assert
        assertThrows(RuntimeException.class, () -> rewardsService.calculateTotalRewardPoints(999L));
    }
    
    @Test
    void calculateMonthlyRewardPoints_WithNoTransactions_ReturnsEmptyMap() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        when(transactionRepository.findByCustomerIdAndTimestampGreaterThanEqual(anyLong(), any()))
            .thenReturn(List.of());
        
        // Act
        Map<String, Object> result = rewardsService.calculateMonthlyRewardPoints(1L);
        
        // Assert
        assertNotNull(result);
        assertTrue(((Map<?, ?>) result.get("monthlyPoints")).isEmpty());
    }
    
    @Test
    void calculateTotalRewardPoints_WithNoTransactions_ReturnsZeroPoints() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        when(transactionRepository.findByCustomerIdAndTimestampGreaterThanEqual(anyLong(), any()))
            .thenReturn(List.of());
        
        // Act
        Map<String, Object> result = rewardsService.calculateTotalRewardPoints(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(0, result.get("totalPoints"));
    }
} 