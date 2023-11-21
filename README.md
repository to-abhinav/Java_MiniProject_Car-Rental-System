# Java_MiniProject_Car-Rental-System
# Car Rental System

## Overview

This is a Java-based mini project for a Car Rental System, developed as a semester project. The system is implemented using Maven for project management. It features a Command Line Interface (CLI) where sellers can add new cars for rent, and customers can rent cars after registering using their email and an OTP sent to their email address.

The project utilizes the JavaMail API for sending OTPs to customer emails and employs file handling to store car data in a `carDatabase.txt` file. Additionally, the system includes a billing system, and customers can return rented cars.

## Features

- **Car Management:**
  - Sellers can add new cars for rent.
  - Car data is stored in a `carDatabase.txt` file using file handling.

- **Customer Registration:**
  - Customers need to register using their email.
  - An OTP is sent to the registered email address for verification.

- **Email Verification:**
  - JavaMail API is used to send OTPs for customer registration.

- **Rental System:**
  - Customers can rent cars after successful registration.
  - A billing system is integrated to calculate the rental charges.

- **Car Return:**
  - Customers can return rented cars, completing the rental process.

## Prerequisites

- Java Development Kit (JDK)
- Maven

## How to Run
Clone the repository or download the Zip
suggested IDE
-Intellij
-Eclipse
