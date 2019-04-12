package com.java.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.java.dto.Employee;
import com.java.dto.RequestForm;
import com.java.exception.DatabaseException;
import com.java.repository.EmployeeRepository;
import com.java.repository.ManagerRepository;

@WebServlet("/resolvepending") //view pending, I don't have time to change this right now
public class ManagerResolvePending extends HttpServlet{
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		//this is what i have in the first place
		if(session.getAttribute("Employee")==null) {
			request.getRequestDispatcher("html/Login.html").forward(request, response);
		}
			//Employee emp =(Employee) session.getAttribute("Employee"); //i'm having the session store employee object
			ManagerRepository mgRp = new ManagerRepository();
			Gson gson = new Gson();
			
			List<RequestForm> list_form = new ArrayList<>();//list of pending forms
			try {
				list_form = mgRp.getAllPending(); //getting all the pending forms from the db
				
				response.setContentType("application/json");
				response.getWriter().println(gson.toJson(list_form));
				
			} catch (DatabaseException e) {
				e.printStackTrace();
				//response.sendRedirect("Login");
				request.getRequestDispatcher("html/ManagerViewPending.html").forward(request, response);
			}
	
	}
}
