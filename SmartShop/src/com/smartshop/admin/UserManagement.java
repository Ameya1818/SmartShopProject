package com.smartshop.admin;

import java.sql.*;
import com.smartshop.util.DBConnection;

public class UserManagement {

	// View all registered users with basic details
	public void viewAllUsers() {
		// SQL query to fetch user ID, username, and email from users table
		String sql = "SELECT user_id, username, email FROM users";

		try (Connection con = DBConnection.getConnection(); // Get DB connection
				Statement stmt = con.createStatement(); // Create statement
				ResultSet rs = stmt.executeQuery(sql)) { // Execute query

			// Print table header
			System.out.println("UserID | Username | Email");
			System.out.println("---------------------------------");

			// Loop through result set and print user details
			while (rs.next()) {
				System.out.println(
						rs.getInt("user_id") + " | " + rs.getString("username") + " | " + rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle database errors
		}
	}
}
