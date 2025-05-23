# Point of Sale System (IV1350 Seminar 4)

This project is part of the course **IV1350 - Object-Oriented Design**, specifically for **Seminar 4**. It represents a simplified **Point of Sale (POS)** system implemented in Java. The system follows object-oriented principles with a focus on clean architecture, low coupling, and high cohesion.

## Overview

The project is divided into two main tasks:

### Task 1 – The Exception Handling

- Use exception(s) to handle alternative flow 3-4a in Process Sale.
  - Handle invalid ItemID
  - Handle database connection failure
- Program should produce output for UI and log into a file.
- Unit tests are required for the custom exception classes.

### Task 2 – Implementation of Design Patterns

- Implementation of Observer pattern that shows the total sum of the costs of the Sale.
  - Handled by two classes; TotalRevenueView and TotalRevenueFileOutput.
- Option: 
  - Implement two other design patterns. 
    - The strategy and singleton patterns are implemented in this case.
      - The strategy is implemented on Discount.
      - The singleton is implemented on DiscountDatabase.

## Technologies Used

- Java
- IntelliJ IDEA
- JUnit 5
- Maven