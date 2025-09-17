package com.smartshop.service;

import com.smartshop.dao.ProductDAO;
import com.smartshop.model.Product;
import com.smartshop.util.InputUtil;

import java.util.*;

/**
 * Service class to handle product-related operations for users, such as viewing
 * all products and searching products by ID or keyword.
 */
public class ProductService {

	// DAO object to interact with the product database
	private ProductDAO dao = new ProductDAO();

	/**
	 * Displays all available products in the system. Fetches data from ProductDAO
	 * and prints in a tabular format.
	 */
	public void viewAllProducts() {
		System.out.println("Product ID | Name | Description | Price | Quantity");
		System.out.println("---------------------------------------------------");
		for (Product p : dao.getAllProducts()) {
			System.out.println(p); // Uses Product.toString() for display
		}
	}

	/**
	 * Allows the user to search or view products. User can search either by: 1.
	 * Product ID 2. Product Name or Keyword
	 */
	public void searchOrViewProduct() {
		Scanner sc = InputUtil.SCANNER;;

		// Menu for search options
		System.out.println("Search Product By:");
		System.out.println("1. Product ID");
		System.out.println("2. Product Name/Keyword");
		System.out.print("Enter choice >> ");
		int choice = sc.nextInt();
		sc.nextLine(); // consume newline after int input

		// Search by Product ID
		if (choice == 1) {
			System.out.print("Enter Product ID >> ");
			int id = sc.nextInt();
			Product p = dao.getProductById(id);

			if (p != null) {
				// Display product details
				System.out.println("Product Name: " + p.getName());
				System.out.println("Description: " + p.getDescription());
				System.out.println("Price: " + p.getPrice());
				System.out.println("Available Quantity: " + p.getQuantity());
			} else {
				System.out.println("Product not found!");
			}

			// Search by Name/Keyword
		} else if (choice == 2) {
			System.out.print("Enter product name or keyword >> ");
			String key = sc.nextLine().toLowerCase();
			boolean found = false;

			// Loop through all products and search by name/description
			for (Product p : dao.getAllProducts()) {
				if (p.getName().toLowerCase().contains(key) || p.getDescription().toLowerCase().contains(key)) {
					System.out.println(p);
					found = true;
				}
			}

			if (!found) {
				System.out.println("No matching products found!");
			}

		} else {
			// Invalid menu option
			System.out.println("Invalid choice!");
		}
	}
}
