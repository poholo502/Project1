package com.java.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dto.Employee;
import com.java.exception.DatabaseException;
import com.java.repository.EmployeeRepository;

@WebServlet("/LoginController")
public class LoginController {

/*	public static String Login(HttpServletRequest request) throws SQLException, DatabaseException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		EmployeeRepository erepository = new EmployeeRepository();
		Employee employee = new Employee();
		
		employee = erepository.approvingLogin(username);
		System.out.println("login: before if loop");
		
		if(username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {
			System.out.println("login: inside if loop");
			request.getSession().setAttribute("Employee", employee);
			return "/html/welcomPage.html";
		}
		return null;
		
		
		
	}*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Employee emp = new Employee();
		EmployeeRepository erepository = new EmployeeRepository();
		
		try {
			emp = erepository.approvingLogin(username);
			
			if(username.equals(emp.getUsername()) && password.equals(emp.getPassword())) {
				System.out.println("login: inside if loop");
				request.getSession().setAttribute("Employee", emp);
				response.sendRedirect("welcomPage.html");
			}else {
				response.sendRedirect("Login.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}