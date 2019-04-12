package com.java.dto;

public class Employee {
	private int employee_id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int employee_role_id;
	
	public Employee(){
		
	}
	
	public Employee(String username, String password){//constructor to take only username and password
												//might need more constructors
		this.username=username;
		this.password=password;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmployee_role_id() {
		return employee_role_id;
	}

	public void setEmployee_role_id(int employee_role_id) {
		this.employee_role_id = employee_role_id;
	}
	
	public String toString() {
		return "Employee Id: " + employee_id + "\nUsername: " + username + "\nPassword: *********\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nEmail: "
				+ email +"\nEmployee Role Id: " + employee_role_id;
	}

	public Employee(int employee_id, String username, String password, String firstName, String lastName, String email,
			int employee_role_id) {
		super();
		this.employee_id = employee_id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.employee_role_id = employee_role_id;
	}
}
