package com.smartshop.service;

import java.sql.*;
import java.util.*;

import com.smartshop.model.User;
import com.smartshop.util.DBConnection;
import com.smartshop.util.InputUtil;

public class UserService {

    // Register user with validation
    public String registerUser(User user) {
        if (user.getFirstName().isEmpty() || !user.getFirstName().matches("[a-zA-Z]+"))
            return "Invalid First Name!";
        if (user.getLastName().isEmpty() || !user.getLastName().matches("[a-zA-Z]+"))
            return "Invalid Last Name!";
        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$"))
            return "Invalid Email!";
        if (!user.getMobile().matches("\\d{10}"))
            return "Invalid Mobile!";
        if (user.getCity().isEmpty())
            return "City cannot be empty!";

        //  Password validation
        String password = user.getPassword();
        String passwordRegex = "^(?=.*[0-9])"           // at least 1 digit
                             + "(?=.*[a-z])"            // at least 1 lowercase
                             + "(?=.*[A-Z])"            // at least 1 uppercase
                             + "(?=.*[@#$%^&+=!])"      // at least 1 special char
                             + "(?=\\S+$)"              // no whitespace
                             + ".{8,}$";                // at least 8 chars

        if (!password.matches(passwordRegex)) {
            return "Invalid Password! Must be at least 8 characters long, contain upper & lower case letters, a digit, and a special character.";
        }

        try (Connection con = DBConnection.getConnection()) {
            // Duplicate username check
            String checkSql = "SELECT * FROM users WHERE username=?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, user.getUsername());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next())
                return "Username already taken!";

            // Insert new user
            String sql = "INSERT INTO users(first_name,last_name,username,password,city,email,mobile,role) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getCity());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getMobile());
            stmt.setString(8, "user"); // default role
            int rows = stmt.executeUpdate();
            return rows > 0 ? "User registered successfully!" : "Registration failed!";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Database error!";
        }
    }

    // Test registration
    public void testRegisterUser() {
        Scanner sc = InputUtil.SCANNER;
        System.out.print("First Name >> ");
        String fn = sc.nextLine();
        System.out.print("Last Name >> ");
        String ln = sc.nextLine();
        System.out.print("Username >> ");
        String un = sc.nextLine();
        System.out.print("Password >> ");
        String pw = sc.nextLine();
        System.out.print("City >> ");
        String city = sc.nextLine();
        System.out.print("Email >> ");
        String email = sc.nextLine();
        System.out.print("Mobile >> ");
        String mob = sc.nextLine();

        User u = new User(fn, ln, un, pw, city, email, mob);
        System.out.println(registerUser(u));
    }

    // Login
    public String loginUser(String username, String password) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String role = rs.getString("role");
                return "SUCCESS:" + role + ":" + firstName;
                // Example: SUCCESS:admin:Ameya OR SUCCESS:user:Atul
            } else {
                return "ERROR: Invalid username or password!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR: Database error!";
        }
    }

    // Get user role
    public String getUserRole(String username) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT role FROM users WHERE username=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user"; // default
    }

    // Fetch user ID by username
    public int getUserId(String username) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT user_id FROM users WHERE username=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return rs.getInt("user_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
