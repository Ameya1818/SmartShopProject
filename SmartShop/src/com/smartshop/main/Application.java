package com.smartshop.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.smartshop.admin.*;
import com.smartshop.service.CartService;
import com.smartshop.service.ProductService;
import com.smartshop.service.PurchaseService;
import com.smartshop.service.UserService;

public class Application {

    public void startApplication() {

        Scanner sc = new Scanner(System.in);

        // ===== Initialize Services =====
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        PurchaseService purchaseService = new PurchaseService();
        CartService cartService = new CartService();

        // ===== Initialize Admin Management Classes =====
        ProductManagement productManagement = new ProductManagement();
        UserManagement userManagement = new UserManagement();
        PurchaseHistory purchaseHistory = new PurchaseHistory();

        // ===== Session Variables =====
        boolean loggedIn = false;
        int currentUserId = -1;
        String currentRole = "user"; // default

        // ===== Main Loop =====
        while (true) {
            try {
                System.out.println("\n===== MAIN MENU =====");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. View Products");
                System.out.println("4. Search Products");
                System.out.println("5. Add to Cart");
                System.out.println("6. View Cart");
                System.out.println("7. Purchase");
                System.out.println("8. Purchase History");
                System.out.println("0. Exit");
                System.out.print("Enter Choice >> ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                // ===== Admin Menu Redirect =====
                if (loggedIn && currentRole.equalsIgnoreCase("admin")) {
                    showAdminMenu(sc, productManagement, userManagement, purchaseHistory);
                    loggedIn = false; // after logout
                    continue;
                }

                // ===== Handle User Choices =====
                switch (choice) {
                    case 1: // Register
                        userService.testRegisterUser();
                        break;

                    case 2: // Login
                        System.out.print("Username >> ");
                        String username = sc.nextLine();
                        System.out.print("Password >> ");
                        String password = sc.nextLine();

                        String result = userService.loginUser(username, password);
                        System.out.println(result);

                        if (result.toLowerCase().contains("success")) {
                            loggedIn = true;
                            currentUserId = userService.getUserId(username);
                            currentRole = userService.getUserRole(username);
                            System.out.println("Logged in as " + currentRole + " (userId: " + currentUserId + ")");

                            // ===== Admin check =====
                            if (currentRole.equalsIgnoreCase("admin")) {
                                showAdminMenu(sc, productManagement, userManagement, purchaseHistory);
                                loggedIn = false;  // after admin logs out
                                currentRole = "user";
                                currentUserId = -1;
                            }
                        } else {
                            loggedIn = false;
                            currentUserId = -1;
                            currentRole = "user";
                        }
                        break;


                    case 3: // View products (guest allowed)
                        productService.viewAllProducts();
                        break;

                    case 4: // Search products (guest allowed)
                        productService.searchOrViewProduct();
                        break;

                    case 5: // Add to cart
                        if (!loggedIn) {
                            System.out.println("You are browsing as guest. Please register or login to add items to cart.");
                            break;
                        }
                        System.out.print("Product ID >> ");
                        int pid = sc.nextInt();
                        System.out.print("Quantity >> ");
                        int qty = sc.nextInt();
                        cartService.addToCart(currentUserId, pid, qty);
                        break;

                    case 6: // View cart
                        if (!loggedIn) {
                            System.out.println("You are browsing as guest. Please register or login to view your cart.");
                            break;
                        }
                        cartService.viewCart(currentUserId);
                        break;

                    case 7: // Checkout / purchase
                        if (!loggedIn) {
                            System.out.println("You are browsing as guest. Please register or login to purchase.");
                            break;
                        }
                        purchaseService.checkout(currentUserId, cartService);
                        break;

                    case 8: // Purchase history
                        if (!loggedIn) {
                            System.out.println("You are browsing as guest. Please register or login to view purchase history.");
                            break;
                        }
                        purchaseService.viewPurchaseHistory(currentUserId);
                        break;

                    case 0: // Exit
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                sc.nextLine();
            }
        }
    }

    // ===== Admin Menu =====
    private static void showAdminMenu(Scanner sc, ProductManagement productManagement, UserManagement userManagement,
                                      PurchaseHistory purchaseHistory) {
        while (true) {
            try {
                System.out.println("\n===== ADMIN MENU =====");
                System.out.println("1. Add New Product");
                System.out.println("2. View Product Stock by ID");
                System.out.println("3. View All Registered Users");
                System.out.println("4. View User Purchase History");
                System.out.println("5. Update Product Details");
                System.out.println("6. Delete Product from Inventory");
                System.out.println("7. Logout");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        productManagement.addProduct();
                        break;
                    case 2:
                        productManagement.viewProductStock();
                        break;
                    case 3:
                        userManagement.viewAllUsers();
                        break;
                    case 4:
                        purchaseHistory.viewUserPurchaseHistory();
                        break;
                    case 5:
                        productManagement.updateProduct();
                        break;
                    case 6:
                        productManagement.deleteProduct();
                        break;
                    case 7:
                        System.out.println("Logging out from ADMIN...");
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Unexpected error in Admin Menu: " + e.getMessage());
                sc.nextLine();
            }
        }
    }
}
