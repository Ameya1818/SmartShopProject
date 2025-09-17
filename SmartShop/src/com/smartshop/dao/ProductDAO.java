package com.smartshop.dao;

import com.smartshop.model.Product;
import com.smartshop.util.DBConnection;
import java.sql.*;
import java.util.*;

public class ProductDAO {

	// ✅ Fetch all products from the database and return them as a List
	public List<Product> getAllProducts() {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT * FROM products ORDER BY product_name"; // SQL query to get all products ordered by name
		try (Connection con = DBConnection.getConnection(); // Establish DB connection
				Statement st = con.createStatement(); // Create statement
				ResultSet rs = st.executeQuery(sql)) { // Execute query and get results
			while (rs.next()) {
				// Create Product object from result set and add to list
				list.add(new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("description"),
						rs.getDouble("price"), rs.getInt("quantity")));
			}
		} catch (Exception e) {
			e.printStackTrace(); // Print exception for debugging
		}
		return list; // Return the list of products
	}

	// ✅ Fetch a single product by its ID
	public Product getProductById(int id) {
		String sql = "SELECT * FROM products WHERE product_id=?"; // SQL query with parameter
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id); // Set product_id in the query
			ResultSet rs = ps.executeQuery(); // Execute query
			if (rs.next()) {
				// Return Product object if found
				return new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getString("description"),
						rs.getDouble("price"), rs.getInt("quantity"));
			}
		} catch (Exception e) {
			e.printStackTrace(); // Print exception if error occurs
		}
		return null; // Return null if no product found
	}

	// ✅ Update product stock after purchase
	public boolean updateStock(int productId, int newQty) {
		String sql = "UPDATE products SET quantity=? WHERE product_id=?"; // SQL update query
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, newQty); // Set new quantity
			ps.setInt(2, productId); // Set product_id
			return ps.executeUpdate() > 0; // Returns true if update is successful
		} catch (Exception e) {
			e.printStackTrace(); // Print exception if update fails
		}
		return false; // Return false if update fails
	}

}
