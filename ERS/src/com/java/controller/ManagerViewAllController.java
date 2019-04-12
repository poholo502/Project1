package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.java.dto.Employee;
import com.java.dto.RequestForm;
import com.java.exception.DatabaseException;
import com.java.repository.ManagerRepository;

@WebServlet("/allrequestform")
public class ManagerViewAllController extends HttpServlet{
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		ObjectMapper om = new ObjectMapper(); //object mapper from com.fasterxml.jackson.databind.ObjectMapper
		
		
		if(session.getAttribute("Employee")==null) {
			request.getRequestDispatcher("/Login.html").forward(request, response);
		}
			
			ManagerRepository mgRp = new ManagerRepository();
			Gson gson = new Gson();
			
			List<RequestForm> list_form = new ArrayList<>();//list of pending forms
			try {
				list_form = mgRp.getAllForms();
				response.setContentType("application/json");
				response.getWriter().println(gson.toJson(list_form));
				
			} catch (DatabaseException e) {
				e.printStackTrace();
				request.getRequestDispatcher("allrequestform").forward(request, response);
			}
	}
}
