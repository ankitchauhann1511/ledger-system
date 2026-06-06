# Ledger System - Double Entry Accounting

## Project Overview

The Ledger System is a fintech-inspired Double Entry Accounting platform developed using Java, Spring Boot, Spring Data JPA, and MySQL.

The system ensures accurate financial transaction recording through double-entry accounting principles, where every transaction maintains a balance between debit and credit entries. The application also includes audit logging, account hierarchy management, multi-currency support, exchange rate reconciliation, and financial report generation.

This project demonstrates backend development skills, database design, REST API development, transaction management, and financial system architecture.

---

## Key Features

### 1. Double Entry Accounting Engine

* Supports Debit and Credit transactions
* Enforces accounting balance rules
* Prevents invalid journal entries
* Ensures total debit equals total credit

### 2. Account Management

* Create and manage ledger accounts
* Account categorization

  * Assets
  * Liabilities
  * Equity
  * Revenue
  * Expenses
* Account balance tracking

### 3. Chart of Accounts Hierarchy

* Parent-child account relationships
* Hierarchical account structure
* Supports enterprise-level accounting organization

Example:

ASSETS

* Cash
* Bank Account
* Accounts Receivable

LIABILITIES

* Loan Payable
* Accounts Payable

REVENUE

* Sales Revenue

EXPENSES

* Rent Expense
* Salary Expense

---

### 4. Journal Entry Management

* Create financial transactions
* Multiple journal lines per transaction
* Automatic validation
* Persistent transaction storage

Example:

Cash Account          Debit 1000
Sales Revenue         Credit 1000

---

### 5. Audit Logging System

* Immutable transaction history
* Tracks all journal entry operations
* Maintains complete audit trail
* Supports future compliance requirements

---

### 6. Balance Verification Engine

Automatically updates account balances after journal entry posting.

Example:

Before Transaction

Cash Account = 0

Transaction

Debit Cash 1000

After Transaction

Cash Account = 1000

---

### 7. Multi-Currency Support

Supports multiple currencies including:

* USD
* INR
* EUR
* GBP

Features:

* Currency management
* Exchange rate management
* Currency conversion
* Exchange rate reconciliation

---

### 8. Financial Reporting

#### Trial Balance

Displays debit and credit balances for all accounts.

#### Profit & Loss Statement

Calculates:

Net Profit = Revenue - Expenses

#### Balance Sheet

Displays:

Assets = Liabilities + Equity

---

## Technology Stack

### Backend

* Java 17
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate

### Database

* MySQL

### Build Tool

* Maven

### Utilities

* Lombok
* Validation API

### Testing Tools

* Postman
* MySQL Workbench

---

## Project Structure

src/main/java

com.ledger

├── controller

├── service

├── repository

├── entity

├── dto

├── config

├── enums

├── exception

├── util

├── security

└── audit

---

## Database Tables

### accounts

Stores all accounting accounts.

### journal_entries

Stores transaction headers.

### journal_lines

Stores debit and credit records.

### audit_logs

Stores audit trail information.

### currencies

Stores supported currencies.

### exchange_rates

Stores currency conversion rates.

---

## REST APIs

### Account APIs

POST /api/accounts

GET /api/accounts

GET /api/accounts/{id}

DELETE /api/accounts/{id}

---

### Journal Entry APIs

POST /api/journal

GET /api/journal

GET /api/journal/{id}

---

### Audit APIs

GET /api/audit

---

### Currency APIs

POST /api/currency

POST /api/currency/rate

GET /api/currency/convert

---

### Reporting APIs

GET /api/reports/trial-balance

GET /api/reports/profit-loss

GET /api/reports/balance-sheet

---

## Validation Rules

### Journal Entry Validation

* Total Debit must equal Total Credit
* Journal line cannot contain both debit and credit amounts
* Invalid entries are rejected

### Balance Verification

* Account balances update automatically
* Financial reports use live balances

---

## Sample Transaction

Transaction:

Cash Sale of ₹5000

Journal Entry:

Cash Account        Debit 5000

Sales Revenue       Credit 5000

Result:

Cash Balance        +5000

Revenue Balance     +5000

Audit Log Created

---

## Future Enhancements

* JWT Authentication
* Role-Based Access Control
* PDF Report Export
* Excel Report Export
* Dashboard Analytics
* Real-Time Exchange Rate API Integration
* Transaction Reversal Support
* Financial Year Closing Process

---

## Author

Ankit Chauhan

Backend Developer

Java | Spring Boot | REST APIs | MySQL

---

## Submission

This project was developed as part of a backend engineering internship assignment focused on designing and implementing a fintech-grade double-entry accounting ledger system.
