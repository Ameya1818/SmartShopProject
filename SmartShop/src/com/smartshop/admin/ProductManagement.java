package com.smartshop.admin;

import java.sql.*;
import java.util.Scanner;
import com.smartshop.util.DBConnection;
import com.smartshop.util.InputUtil;

public class ProductManagement {

	// ✅ Add a new product to the database
	public void addProduct() {
		Scanner sc = InputUtil.SCANNER;;
		System.out.print("Enter Product Name >> ");
		String name = sc.nextLine(); // Input product name
		System.out.print("Enter Description >> ");
		String desc = sc.nextLine(); // Input description
		System.out.print("Enter Price >> ");
		double price = sc.nextDouble(); // Input price
		System.out.print("Enter Quantity >> ");
		int qty = sc.nextInt(); // Input quantity

		String sql = "INSERT INTO products(product_name, description, price, quantity) VALUES (?,?,?,?)";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, name); // Set product name
			ps.setString(2, desc); // Set description
			ps.setDouble(3, price); // Set price
			ps.setInt(4, qty); // Set quantity

			int rows = ps.executeUpdate(); // Execute insert
			if (rows > 0) {
				System.out.println(" Product added successfully!");
			} else {
				System.out.println(" Failed to add product!");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Print error if insertion fails
		}
	}

	// ✅ View available stock of a specific product
	public void viewProductStock() {
		Scanner sc = InputUtil.SCANNER;;
		System.out.print("Enter Product ID to check stock >> ");
		int productId = sc.nextInt(); // Input product ID

		String sql = "SELECT quantity FROM products WHERE product_id=?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, productId); // Set product ID
			ResultSet rs = ps.executeQuery(); // Execute query

			if (rs.next()) {
				System.out.println("Available Quantity >> " + rs.getInt("quantity")); // Show stock
			} else {
				System.out.println(" Product not found for ID: " + productId); // Not found
			}
		} catch (Exception e) {
			e.printStackTrace(); // Print error if query fails
		}
	}

	// ✅ Update product details (name, description, price, or quantity)
	public void updateProduct() {
		Scanner sc = InputUtil.SCANNER;;
		System.out.print("Enter Product ID to update >> ");
		int productId = sc.nextInt(); // Input product ID
		sc.nextLine(); // Consume leftover newline

		// Show update options
		System.out.println("Select field to update:");
		System.out.println("1. Name");
		System.out.println("2. Description");
		System.out.println("3. Price");
		System.out.println("4. Quantity");
		int choice = sc.nextInt();
		sc.nextLine(); // Consume leftover newline

		String sql = null; // SQL query
		Object newValue = null; // New value entered by user

		// Decide which field to update based on choice
		switch (choice) {
		case 1:
			System.out.print("Enter new name >> ");
			newValue = sc.nextLine();
			sql = "UPDATE products SET product_name=? WHERE product_id=?";
			break;
		case 2:
			System.out.print("Enter new description >> ");
			newValue = sc.nextLine();
			sql = "UPDATE products SET description=? WHERE product_id=?";
			break;
		case 3:
			System.out.print("Enter new price >> ");
			newValue = sc.nextDouble();
			sql = "UPDATE products SET price=? WHERE product_id=?";
			break;
		case 4:
			System.out.print("Enter new quantity >> ");
			newValue = sc.nextInt();
			sql = "UPDATE products SET quantity=? WHERE product_id=?";
			break;
		default:
			System.out.println(" Invalid choice!");
			return;
		}

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, newValue); // Set updated value
			ps.setInt(2, productId); // Set product ID

			int rows = ps.executeUpdate(); // Execute update
			if (rows > 0) {
				System.out.println("Product updated successfully!");
			} else {
				System.out.println(" Product ID not found.");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Print error if update fails
		}
	}

	// ✅ Delete a product from the database
	public void deleteProduct() {
		Scanner sc = InputUtil.SCANNER;
		System.out.print("Enter Product ID to delete >> ");
		int id = sc.nextInt(); // Input product ID
		sc.nextLine(); // Consume leftover newline
		System.out.print("Are you sure you want to delete this product? (Yes/No) >> ");
		String confirm = sc.nextLine(); // Confirm deletion

		if (!confirm.equalsIgnoreCase("yes")) {
			System.out.println(" Deletion cancelled."); // Cancel if not confirmed
			return;
		}

		String sql = "DELETE FROM products WHERE product_id=?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id); // Set product ID

			int rows = ps.executeUpdate(); // Execute delete
			if (rows > 0) {
				System.out.println("Product deleted successfully!");
			} else {
				System.out.println("Product not found.");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Print error if deletion fails
		}
	}
}
