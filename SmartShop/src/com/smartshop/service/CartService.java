package com.smartshop.service;

import java.util.*;

import com.smartshop.dao.ProductDAO;
import com.smartshop.model.Cart;
import com.smartshop.model.*;

/**
 * Service class to manage cart operations such as
 * adding products, viewing cart, retrieving items, and clearing cart.
 */
public class CartService {

    // Stores cart data for each user (userId -> list of cart items)
    private Map<Integer, List<Cart>> userCarts = new HashMap<>();

    // DAO to fetch product details from database
    private ProductDAO productDAO = new ProductDAO();

    /**
     * Adds a product to the user's cart.
     * Validates product availability and stock before adding.
     *
     * @param userId    ID of the user
     * @param productId ID of the product to be added
     * @param qty       Quantity of the product
     */
    public void addToCart(int userId, int productId, int qty) {
        Product p = productDAO.getProductById(productId);

        // Check if product exists and has enough stock
        if (p == null || p.getQuantity() < qty) {
            System.out.println("Invalid product or insufficient stock.");
            return;
        }

        // Get user's cart or create a new one
        List<Cart> cart = userCarts.getOrDefault(userId, new ArrayList<>());

        // Add the product as a cart item
        cart.add(new Cart(userId, productId, p.getProductName(), p.getPrice(), qty));

        // Update cart in the map
        userCarts.put(userId, cart);

        System.out.println(" Added to cart!");
    }

    /**
     * Displays the cart items of a user.
     *
     * @param userId ID of the user
     */
    public void viewCart(int userId) {
        List<Cart> cart = userCarts.get(userId);

        // If cart is empty
        if (cart == null || cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        // Print cart items
        System.out.println("Your Cart:");
        for (Cart c : cart) System.out.println(c);
    }

    /**
     * Returns the list of cart items for a user.
     *
     * @param userId ID of the user
     * @return List of Cart objects
     */
    public List<Cart> getCartItems(int userId) {
        return userCarts.getOrDefault(userId, new ArrayList<>());
    }

    /**
     * Clears the cart of a specific user.
     *
     * @param userId ID of the user
     */
    public void clearCart(int userId) {
        userCarts.remove(userId);
    }
}
