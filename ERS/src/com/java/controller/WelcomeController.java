package com.java.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.dto.Employee;

@WebServlet("/welcome")
public class WelcomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		//this is what i have in the first place
		if(session.getAttribute("Employee")!=null) {
			//response.sendRedirect("html/welcomePage.html");
			Employee emp = (Employee) session.getAttribute("Employee");
		}
		
		response.sendRedirect("Login");
	}
		
		

}
