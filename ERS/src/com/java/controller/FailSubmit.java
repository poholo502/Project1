package com.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dto.Employee;

@WebServlet("/failsubmit")
public class FailSubmit extends HttpServlet{
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Employee emp = new Employee();
		//this is what i have in the first place
		if(session.getAttribute("Employee")!=null) {
			emp = (Employee) session.getAttribute("Employee");
			if(emp.getEmployee_role_id()==333) {
				request.getRequestDispatcher("html/FailSubmit.html").forward(request, response);
			//response.sendRedirect("html/FailSubmit.html");
			}else {
				request.getRequestDispatcher("html/ManagerFailSubmit.html").forward(request, response);
			}
		}
		request.getRequestDispatcher("html/Login.html").forward(request, response);
		//response.sendRedirect("Login");
	}
}
