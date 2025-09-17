# SmartShop Project

![Java](https://img.shields.io/badge/Java-8+-blue)
![MySQL](https://img.shields.io/badge/MySQL-8+-orange)
![License](https://img.shields.io/badge/License-MIT-green)

---

## Overview
SmartShop is a **Java-based e-commerce application** designed to manage products, users, and shopping carts efficiently. It follows a modular structure with **role-based access**, making it easy to scale, maintain, and extend.

---

## Features
- **User Features**
  - Register and login
  - Browse products by category
  - Add products to shopping cart
  - Place orders and view order history

- **Admin Features**
  - Add, update, and delete products
  - View all registered users
  - Track user purchase history

- **Validation & Security**
  - Input validations (email, password, quantity)
  - Role-based access control (Admin / User)

---

## Requirements
- Java 8 or higher  
- MySQL 8.0 or higher  
- MySQL Workbench (optional, for database management)  

---

## Installation & Setup

### 1. Clone the repository
```bash
git clone https://github.com/Ameya1818/SmartShopProject.git
cd SmartShopProject
```
### 2. Create the database
```
-- Connect to MySQL
mysql -u <username> -p

-- Create the database
CREATE DATABASE smartshop;

-- Exit MySQL
EXIT;
```
### 3. Import the database dump
```
mysql -u <username> -p smartshop < SmartShopDB.sql
```
### Default Admin Credentials
  - Username: admin
  - Password: admin123
  - Role: admin
Use these credentials to access the admin menu after login.

### Running the Application
1.Open the project in your Java IDE (Eclipse, IntelliJ, etc.).
2.Run the Application.java class.
3.Use the console menu to:
  - Register or login
  - Browse products
  - Add items to cart
  - Place orders
Admin users will see additional menu options

### Project Structure
SmartShopProject/
├─ src/
│  ├─ com.smartshop.admin/       # Admin operations
│  ├─ com.smartshop.model/       # Entity classes
│  ├─ com.smartshop.service/     # Business logic
│  ├─ com.smartshop.dao/         # Database access
│  └─ com.smartshop.main/        # Main application entry
├─ SmartShopDB.sql               # MySQL database dump
└─ README.md                     # Project documentation


