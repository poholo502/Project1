/*package com.java.masterServlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.controller.RequestHelper;
import com.java.exception.DatabaseException;

public class MasterServlet extends HttpServlet{
	
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestHelper.process(request, response);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String targetURL;
		try {
			targetURL = RequestHelper.process(request, response);
			request.getRequestDispatcher(targetURL).forward(request, response);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
*/