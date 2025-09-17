package com.smartshop.model;

public class Cart {
	// User who owns this cart item
	private int userId;

	// Product details
	private int productId;
	private String productName;
	private double price;

	// Quantity of the product added to cart
	private int quantity;

	// Calculated subtotal (price * quantity)
	private double subtotal;

	// Constructor to initialize all fields when a cart item is created
	public Cart(int userId, int productId, String productName, double price, int quantity) {
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.subtotal = price * quantity; // calculate subtotal on creation
	}

	// --- Getters (to fetch values) ---
	public int getUserId() {
		return userId;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}

	// Override toString() to display cart item in a readable format
	@Override
	public String toString() {
		return productName + " | Qty: " + quantity + " | Price: " + price + " | Subtotal: " + subtotal;
	}
}
