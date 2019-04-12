package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet{
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//this is what i have in the first place
		if(session.getAttribute("Employee")==null) {
			//session.removeAttribute("username");
			response.sendRedirect("html/Login.html");
			
			
			//response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			
			//request.getRequestDispatcher("html/Login.html").forward(request, response);
	
		}
		//request.getRequestDispatcher("html/Login.html").forward(request, response);
		session.invalidate();
		response.sendRedirect("Login");
		
		
	}
}
