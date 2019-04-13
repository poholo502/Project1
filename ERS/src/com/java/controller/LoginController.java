package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.java.dto.Employee;
import com.java.exception.DatabaseException;
import com.java.repository.EmployeeRepository;
import com.java.util.DBUtil;

@WebServlet("/Login")
public class LoginController extends HttpServlet{
	//static Logger logger = Logger.getLogger(LoginController.class); 	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		if(session.getAttribute("Employee")==null) {
			//session.removeAttribute("username");
			response.sendRedirect("html/Login.html");
		}
		//response.sendRedirect("html/Login.html");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		/*if(session.getAttribute("Employee")==null) {
			//session.removeAttribute("username");
			response.sendRedirect("html/Login.html");
			
		}*/
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee emp = new Employee();
		EmployeeRepository erepository = new EmployeeRepository();
		
		
		try {
			
			emp = erepository.approvingLogin(username, password);
			//System.out.println(emp);
			if(emp == null) {
				//System.out.println(emp);
				response.sendRedirect("html/Login.html");
				//request.getRequestDispatcher("html/Login.html").forward(request, response);
			}else {
				System.out.println(emp);
			/*if(username.equals(emp.getUsername()) && password.equals(emp.getPassword())) {*/
				if(emp.getEmployee_role_id()==333) {
				request.getSession().setAttribute("Employee", emp);
				response.sendRedirect("html/welcomePage.html");
				}else {
					request.getSession().setAttribute("Employee", emp);
					response.sendRedirect("html/managerHomepage.html");
				}
			}/*else {
				//response.sendRedirect("html/Login.html");
			//}*/
		} catch (SQLException e) {
			//logger.error("SQL query cannot be done" + e.getMessage());
			e.printStackTrace();
		} catch (DatabaseException e) {
			//logger.error("Database connection exception" + e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
}
