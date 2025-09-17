package com.smartshop.dao;

import com.smartshop.model.*;
import com.smartshop.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {

	// ✅ Save a new purchase record into the database
	public boolean savePurchase(Purchase purchase) {
		String sql = "INSERT INTO purchases(user_id, product_id, quantity) VALUES(?,?,?)"; // Insert query
		try (Connection con = DBConnection.getConnection(); // Get DB connection
				PreparedStatement stmt = con.prepareStatement(sql)) { // Prepare statement
			stmt.setInt(1, purchase.getUserId()); // Set user_id
			stmt.setInt(2, purchase.getProductId()); // Set product_id
			stmt.setInt(3, purchase.getQuantity()); // Set purchase quantity
			return stmt.executeUpdate() > 0; // Returns true if insert was successful
		} catch (SQLException e) {
			e.printStackTrace(); // Log exception for debugging
			return false; // Return false if save fails
		}
	}

	// ✅ Fetch purchase history of a specific user
	public List<Purchase> getUserPurchases(int userId) {
		List<Purchase> list = new ArrayList<>();
		// SQL query joins purchases with products to fetch name, price, quantity, and
		// subtotal
		String sql = "SELECT p.product_name, pr.quantity, p.price, (p.price*pr.quantity) as subtotal "
				+ "FROM purchases pr JOIN products p ON pr.product_id=p.product_id " + "WHERE pr.user_id=?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, userId); // Set user_id in the query
			ResultSet rs = ps.executeQuery(); // Execute query
			while (rs.next()) {
				Purchase pu = new Purchase(); // Create Purchase object
				pu.setProductName(rs.getString("product_name")); // Set product name
				pu.setQuantity(rs.getInt("quantity")); // Set purchased quantity
				pu.setPrice(rs.getDouble("price")); // Set product price
				pu.setSubtotal(rs.getDouble("subtotal")); // Set subtotal = price * quantity
				list.add(pu); // Add purchase record to list
			}
		} catch (Exception e) {
			e.printStackTrace(); // Log error if query fails
		}
		return list; // Return purchase history list
	}

}
