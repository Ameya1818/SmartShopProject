package com.smartshop.model;

/**
 * Represents a Product in the SmartShop application.
 * Contains product details such as ID, name, description, price, and quantity.
 */
public class Product {
    
    // ===== Fields (Product attributes) =====
    private int productId;       // Unique identifier for the product
    private String name;         // Name of the product
    private String description;  // Short description of the product
    private double price;        // Price of the product
    private int quantity;        // Available stock/quantity of the product

    // ===== Constructor =====
    public Product(int productId, String name, String description, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // ===== Getters & Setters =====
    
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Alias method for getName() - not strictly required.
     * Can be useful if different naming convention is used in other parts of the project.
     */
    public String getProductName() {
        return name;
    }

    public void setProductName(String productName) {
        this.name = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ===== Utility Methods =====
    
    /**
     * Returns product details in a formatted string for display.
     */
    @Override
    public String toString() {
        return productId + " | " + name + " | " + description + " | " + price + " | " + quantity;
    }
}
