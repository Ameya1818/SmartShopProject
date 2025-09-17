package com.smartshop.model;

/**
 * Represents a Purchase transaction in the SmartShop application. Stores
 * details about the user, product, quantity, and purchase date. Also includes
 * extra fields for displaying purchase/cart information.
 */
public class Purchase {

	// ===== Core Fields =====
	private int purchaseId; // Unique ID for each purchase
	private int userId; // ID of the user who made the purchase
	private int productId; // ID of the purchased product
	private int quantity; // Quantity purchased
	private String purchaseDate;// Date of purchase (format: yyyy-MM-dd or similar)

	// ===== Extra Fields (for display in cart/purchase history) =====
	private String productName; // Product name (joined from Product table)
	private double price; // Price of single unit
	private double subtotal; // Computed subtotal (price * quantity)

	// ===== Constructors =====

	/** Default constructor */
	public Purchase() {
	}

	/** Constructor for full purchase record (with purchase ID and date) */
	public Purchase(int purchaseId, int userId, int productId, int quantity, String purchaseDate) {
		this.purchaseId = purchaseId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.purchaseDate = purchaseDate;
	}

	/** Constructor for cart usage (before purchase is confirmed) */
	public Purchase(int userId, int productId, int quantity) {
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}

	// ===== Getters & Setters =====

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	// ===== Utility Method =====

	/**
	 * Returns a string summary of the purchase for display in cart/history.
	 */
	@Override
	public String toString() {
		return productName + " | Qty: " + quantity + " | Price: " + price + " | Subtotal: " + subtotal;
	}
}
