# Spring Boot Rewards Calculator

A Spring Boot application that calculates reward points for customer transactions based on the following rules:
- 2 points for every dollar spent over $100 in each transaction
- 1 point for every dollar spent between $50 and $100 in each transaction

## Features

- RESTful API endpoints for customer and transaction management
- H2 in-memory database for data storage
- Exception handling with custom exceptions
- Lombok for reducing boilerplate code
- Swagger UI for API documentation

## API Endpoints

### Customer Endpoints
- `GET /api/customers` - Get all customers with their transaction history
- `GET /api/rewards/customer/{id}/total` - Get total rewards points for a customer
- `GET /api/rewards/customer/{id}/monthly` - Get monthly rewards points for a customer

## Technologies Used

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Lombok
- Maven

## Getting Started

1. Clone the repository
2. Make sure you have Java 17 and Maven installed
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application at `http://localhost:8080`

## Testing the API

You can test the API endpoints using curl or any API testing tool like Postman. Example:

```bash
# Get all customers with their transaction history
curl http://localhost:8080/api/customers

# Get total rewards for a customer
curl http://localhost:8080/api/rewards/customer/1/total

# Get monthly rewards for a customer
curl http://localhost:8080/api/rewards/customer/1/monthly
```

## Sample Response Examples

### Get All Customers
```json
[
  {
    "id": 1,
    "name": "Priti",
    "email": "priti@example.com",
    "transactions": [
      {
        "id": 1,
        "amount": 120.00,
        "timestamp": "2025-03-15T10:00:00"
      },
      // ... more transactions
    ]
  }
  // ... more customers
]
```

### Get Total Rewards
```json
{
  "customerId": 1,
  "totalPoints": 365,
  "customerName": "Priti"
}
```

### Get Monthly Rewards
```json
{
  "customerId": 1,
  "monthlyPoints": {
    "2025-01": 250,
    "2025-02": 25,
    "2025-03": 90
  },
  "customerName": "Priti"
}
```

## Points Calculation Rules

The application calculates reward points based on the following rules:
1. For transactions over $100:
   - 2 points for every dollar spent over $100
   - 1 point for every dollar between $50 and $100
2. For transactions between $50 and $100:
   - 1 point for every dollar spent

Example:
- A transaction of $120 earns 90 points:
  - 2 points × $20 (amount over $100) = 40 points
  - 1 point × $50 (amount between $50-$100) = 50 points
  - Total = 90 points

## Project Structure

```
src/main/java/com/rewards/
├── RewardsCalculatorApplication.java
├── controller/
│   └── CustomerRewardsController.java
├── model/
│   ├── Customer.java
│   └── Transaction.java
├── repository/
│   ├── CustomerRepository.java
│   └── TransactionRepository.java
├── service/
│   └── RewardsService.java
└── exception/
    ├── CustomerNotFoundException.java
    ├── InvalidInputException.java
    └── GlobalExceptionHandler.java
``` 