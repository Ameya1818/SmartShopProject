package com.smartshop.admin;

import java.sql.*;
import java.util.Scanner;
import com.smartshop.util.DBConnection;
import com.smartshop.util.InputUtil;

public class PurchaseHistory {

    // ✅ View purchase history of a user by username
    public void viewUserPurchaseHistory() {
        Scanner sc = InputUtil.SCANNER;;
        System.out.print("Enter Username to view purchase history >> ");
        String uname = sc.nextLine(); // Take username input

        // SQL query to fetch purchase history:
        // Join purchases, products, and users tables
        // Show product ID, name, quantity, purchase date, and price
        String sql = "SELECT pr.product_id, pr.product_name, p.quantity, p.purchase_date, pr.price "
                + "FROM purchases p JOIN products pr ON p.product_id = pr.product_id "
                + "JOIN users u ON p.user_id = u.user_id "
                + "WHERE u.username=? ORDER BY p.purchase_date DESC";

        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, uname); // Bind username
            ResultSet rs = ps.executeQuery(); // Execute query

            // Print table header
            System.out.println("ProductID | Name | Qty | Date | Price");
            System.out.println("----------------------------------------");

            boolean found = false; // Track if purchases exist
            while (rs.next()) {
                found = true;
                // Print purchase details row by row
                System.out.println(
                    rs.getInt("product_id") + " | " 
                    + rs.getString("product_name") + " | "
                    + rs.getInt("quantity") + " | " 
                    + rs.getDate("purchase_date") + " | " 
                    + rs.getDouble("price")
                );
            }

            // If no records found
            if (!found) {
                System.out.println(" No purchases found for this user.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
    }
}
