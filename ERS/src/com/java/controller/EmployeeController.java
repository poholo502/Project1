package com.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dto.Employee;
import com.java.repository.EmployeeRepository;

@WebServlet("/employeeInfo")
public class EmployeeController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		//this is what i have in the first place
		if(session.getAttribute("Employee")!=null) {
			Employee emp = (Employee) session.getAttribute("Employee");
			if(emp.getEmployee_role_id()==333) {
				request.getSession().setAttribute("Employee", emp);
				response.sendRedirect("html/employeeInfo.html");
				}else {
					request.getSession().setAttribute("Employee", emp);
					response.sendRedirect("html/ManagerViewEmployeeInfo.html");
				}
			}
		
		//response.sendRedirect("Login");
	}
}