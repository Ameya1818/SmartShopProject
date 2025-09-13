package com.velocity.menuOption;


import java.util.Scanner;

public class MainMenu {
	
	static Scanner scanner = new Scanner(System.in);
	public static void displayOption() {
		System.out.println();
		System.out.println("Welcome to E-Commerce Console Application");
		System.out.println("===============================================");
		System.out.println("Please choose an option:");
		System.out.println();
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("3. View Products");
		System.out.println("4. Search Product");
		System.out.println("5. Add to Cart");
		System.out.println("6. View Cart");
		System.out.println("7. View Purchase History");
		System.out.println("8. Exit");
		System.out.println();
		System.out.println("Enter your choice >> ");
		
	}
	
	
	public static void chooseOption() {
		int input;
		String userInput = scanner.nextLine().trim();
		try {
			
			input = Integer.parseInt(userInput);

			switch (input) {
				case 1:
					System.out.println(" Register Page ");
					// call register method
					break;

				case 2:
					System.out.println(" Login Page ");
					// call login method
					break;

				case 3:
					System.out.println("Display Products");
					// call viewProducts()
					break;
				case 4:
					System.out.println("Search Product");
					// call searchProduct()
					break;

				case 5:
					System.out.println("Add to Cart");
					// call addToCart()
					break;

				case 6:
					System.out.println("View Cart");
					// call viewCart()
					break;

				case 7:
					System.out.println("View Purchase History");
					// call viewPurchaseHistory()
					break;
					
		case 8:
			System.out.println("Returning to Main Menu...");
			System.exit(0);
			break;
			
			default:
				System.out.println("❌ Invalid choice. Try again.");
				retryOrMenu();
		}
		}catch(Exception
				e) {
			System.err.println("❌ Invalid input! Please enter numeric value");
			retryOrMenu();
		}
	}
	
	// Give user option to retry or go to main menu
		private static void retryOrMenu() {
			while (true) {
				System.out.print("Do you want to retry? (yes/menu): ");
				String input = scanner.nextLine().trim().toLowerCase();

				if (input.equals("yes")) {
					chooseOption();
					break;
				} else if (input.equals("menu")) {
					displayOption();
					chooseOption();
					break;
				} else {
					System.out.println("❌ Invalid input. Type 'yes' to retry or 'menu' to go to main menu.");
				}
				}
				}
		
	public static void main(String[] args) {
		
			while(true) {
				displayOption();
				chooseOption();
			}
		
	}
}
