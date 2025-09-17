package com.smartshop.service;

import java.util.List;
import java.util.Scanner;

import com.smartshop.dao.ProductDAO;
import com.smartshop.dao.PurchaseDAO;
import com.smartshop.model.Product;
import com.smartshop.util.InputUtil;
import com.smartshop.model.*;

/**
 * Service class to handle purchase-related operations. Provides functionality
 * for: - Direct product purchase - Viewing purchase history - Checkout from the
 * cart
 */
public class PurchaseService {

	// DAO classes to interact with database
	private PurchaseDAO purchaseDAO = new PurchaseDAO();
	private ProductDAO productDAO = new ProductDAO();

	/**
	 * Allows a user to purchase a single product directly by entering product ID
	 * and quantity. Validates stock before saving purchase.
	 *
	 * @param userId ID of the logged-in user
	 */
	public void purchaseProduct(int userId) {
		Scanner sc = InputUtil.SCANNER;;

		System.out.print("Product ID >> ");
		int pid = sc.nextInt();

		System.out.print("Quantity >> ");
		int qty = sc.nextInt();

		// ✅ Check if product exists and has enough stock
		Product p = productDAO.getProductById(pid);
		if (p == null || p.getQuantity() < qty) {
			System.out.println("Not enough stock or invalid product.");
			return;
		}

		// ✅ Save purchase into DB
		if (purchaseDAO.savePurchase(new Purchase(userId, pid, qty))) {
			// Reduce stock after purchase
			productDAO.updateStock(pid, p.getQuantity() - qty);
			System.out.println(" Purchase successful!");
		} else {
			System.out.println(" Purchase failed!");
		}
	}

	/**
	 * Displays the purchase history of a specific user.
	 *
	 * @param userId ID of the logged-in user
	 */
	public void viewPurchaseHistory(int userId) {
		List<Purchase> purchases = purchaseDAO.getUserPurchases(userId);

		if (purchases.isEmpty()) {
			System.out.println("No purchases found.");
			return;
		}

		System.out.println("Your Purchase History:");
		for (Purchase p : purchases) {
			System.out.println(p); // Uses Purchase.toString() for display
		}
	}

	/**
	 * Performs checkout for all items in the user's cart. - Iterates through cart
	 * items - Saves purchases in DB - Updates stock - Clears cart if successful
	 *
	 * @param userId      ID of the logged-in user
	 * @param cartService Service class managing user's cart
	 */
	public void checkout(int userId, CartService cartService) {
		List<Cart> items = cartService.getCartItems(userId);

		if (items.isEmpty()) {
			System.out.println("Cart is empty, nothing to checkout.");
			return;
		}

		boolean success = true;

		// Process each cart item as a purchase
		for (Cart c : items) {
			Purchase purchase = new Purchase(userId, c.getProductId(), c.getQuantity());

			if (!purchaseDAO.savePurchase(purchase)) {
				success = false;
				break;
			}

			// Update stock for purchased product
			Product p = productDAO.getProductById(c.getProductId());
			productDAO.updateStock(c.getProductId(), p.getQuantity() - c.getQuantity());
		}

		// Final status of checkout
		if (success) {
			System.out.println(" Checkout successful!");
			cartService.clearCart(userId); // Empty cart after successful purchase
		} else {
			System.out.println(" Checkout failed!");
		}
	}
}
