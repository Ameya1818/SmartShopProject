package com.smartshop.model;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.*;
import com.smartshop.util.DBConnection;

public class UserService {

    // Register user with strong validation
    public String registerUser(User user) {
        // First Name & Last Name validation
        if (user.getFirstName().isEmpty() || !user.getFirstName().matches("[a-zA-Z]+")) {
            return "Invalid First Name! Only letters allowed and cannot be empty.";
        }
        if (user.getLastName().isEmpty() || !user.getLastName().matches("[a-zA-Z]+")) {
            return "Invalid Last Name! Only letters allowed and cannot be empty.";
        }

        // Email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        if (!emailPattern.matcher(user.getEmail()).matches()) {
            return "Invalid email format!";
        }

        // Mobile validation
        if (!user.getMobile().matches("\\d{10}")) {
            return "Mobile number must be exactly 10 digits!";
        }

        // Password validation
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
        if (!user.getPassword().matches(passwordRegex)) {
            return "Password must be at least 8 characters and include uppercase, lowercase, digit, and special character.";
        }

        // City validation
        if (user.getCity().isEmpty()) {
            return "City cannot be empty!";
        }

        // Database operations
        try (Connection con = DBConnection.getConnection()) {

            // Check duplicate username
            String checkSql = "SELECT * FROM users WHERE username=?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, user.getUsername());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                return "Username already taken! Choose another.";
            }

            // Insert user
            String sql = "INSERT INTO users (first_name, last_name, username, password, city, email, mobile) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getCity());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getMobile());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                return "User registered successfully!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Database error occurred!";
        }

        return "Registration failed!";
    }

    // Test method for registration
    public void testRegisterUser() {
        Scanner sc = new Scanner(System.in);

        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("City: ");
        String city = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Mobile: ");
        String mobile = sc.nextLine();

        User user = new User(firstName, lastName, username, password, city, email, mobile);
        String result = registerUser(user);
        System.out.println(result);
    }

    // User login
    public String loginUser(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return "Login successful! Welcome " + rs.getString("first_name");
            } else {
                return "Invalid username or password!";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Database error occurred!";
        }
    }

    // Test method for login
    public void testLoginUser() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        String result = loginUser(username, password);
        System.out.println(result);
    }
}
