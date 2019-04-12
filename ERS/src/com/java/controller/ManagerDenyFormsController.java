package com.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dto.Employee;
import com.java.exception.DatabaseException;
import com.java.repository.ManagerRepository;

@WebServlet("/deny")
public class ManagerDenyFormsController extends HttpServlet{

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
HttpSession session = request.getSession();
		
		//this is what i have in the first place
		if(session.getAttribute("Employee")==null) {
			request.getRequestDispatcher("html/Login.html").forward(request, response);
		}
		
		int emp_id = Integer.parseInt(request.getParameter("id"));
		Employee manager_id = (Employee) session.getAttribute("Employee");
		ManagerRepository mgRp = new ManagerRepository();
		
		try {
			mgRp.denyFrom(emp_id, manager_id.getEmployee_id());
			response.sendRedirect("html/ManagerResolveForms.html");
		} catch (DatabaseException e) {
			
			e.printStackTrace();
			response.sendRedirect("html/ManagerResolveForms.html");
		}
		
	}
}
