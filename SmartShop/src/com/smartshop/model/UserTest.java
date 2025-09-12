package com.smartshop.model;

import java.util.Scanner;

public class UserTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create objects of service classes
        UserService userService = new UserService();
        // ProductService productService = new ProductService();
        // CartService cartService = new CartService();
        // OrderService orderService = new OrderService();

        while (true) {
            System.out.println("\n=== Smart Shop Test Menu ===");
            System.out.println("1. Register User");
            System.out.println("2. Login User");
            System.out.println("3. Add/View Product");
            System.out.println("4. Add/View Cart");
            System.out.println("5. Place/View Order");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    userService.testRegisterUser(); // Test register
                    break;

                case 2:
                    userService.testLoginUser(); // Test login
                    break;

                case 3:
                    // productService.testProducts();
                    break;

                case 4:
                    // cartService.testCart();
                    break;

                case 5:
                    // orderService.testOrders();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
