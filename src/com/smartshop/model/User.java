package com.smartshop.model;

/**
 * Represents a User in the SmartShop application. Stores personal details,
 * login credentials, and contact information.
 */
public class User {

	// ===== Fields =====
	private int userId; // Auto-generated primary key (DB)
	private String firstName; // User's first name
	private String lastName; // User's last name
	private String username; // Unique username for login
	private String password; // Encrypted password for login
	private String city; // User's city
	private String email; // User's email address
	private String mobile; // User's mobile number

	// ===== Constructors =====

	/** Default constructor (required for frameworks/ORM) */
	public User() {
	}

	/** Constructor for new user registration (userId auto-generated) */
	public User(String firstName, String lastName, String username, String password, String city, String email,
			String mobile) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.city = city;
		this.email = email;
		this.mobile = mobile;
	}

	// ===== Getters & Setters =====

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	// ===== Utility Methods =====

	/**
	 * Returns user details as a formatted string.
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", city=" + city + ", email=" + email + ", mobile=" + mobile
				+ "]";
	}
}
