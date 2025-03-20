# Rewards Calculator API

A Spring Boot application that calculates reward points for customers based on their purchase transactions.

## Features

- Calculate reward points for customers based on transaction amounts
- Points calculation rules:
  - 2 points for every dollar spent over $100 in each transaction
  - 1 point for every dollar spent between $50 and $100 in each transaction
- Monthly and total points calculation for the last three months
- RESTful API endpoints
- H2 in-memory database for development
- Comprehensive test coverage

## Project Structure

```
src/main/java/com/rewards/
├── RewardsCalculatorApplication.java
├── controller/
│   └── RewardsController.java
├── model/
│   ├── Customer.java
│   └── Transaction.java
├── repository/
│   ├── CustomerRepository.java
│   └── TransactionRepository.java
├── service/
│   └── RewardsService.java
├── dto/
│   ├── CustomerRewardPoints.java
│   └── MonthlyRewardPoints.java
└── config/
    └── DataInitializer.java
```

## API Endpoints

### Get Customer Rewards
```
GET /api/rewards/{customerId}
```

Returns the reward points for a specific customer, including:
- Monthly breakdown of points
- Total points for the last three months

Example Response:
```json
{
    "customerId": 1,
    "customerName": "John Doe",
    "monthlyPoints": [
        {
            "month": "March 2024",
            "points": 90
        },
        {
            "month": "February 2024",
            "points": 25
        }
    ],
    "totalPoints": 115
}
```

## Setup and Running

1. Prerequisites:
   - Java 17 or higher
   - Maven

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application:
   - API: http://localhost:8080/api/rewards
   - H2 Console: http://localhost:8080/h2-console

## Testing

The project includes unit tests for the service layer. Run the tests using:

```bash
mvn test
```

## Development

The application uses:
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Lombok
- JUnit 5
- Mockito

## License

This project is licensed under the MIT License. 