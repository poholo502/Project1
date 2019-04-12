package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dto.Employee;
import com.java.dto.RequestForm;
import com.java.exception.DatabaseException;
import com.java.repository.EmployeeRepository;

@WebServlet("/insert")
public class InsertFormController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		//this is what i have in the first place
		if(session.getAttribute("Employee")!=null) {
			response.sendRedirect("html/welcomePage.html");
		}
		
		response.sendRedirect("Login");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("Employee")==null) {
			response.sendRedirect("html/Login.html");
		}
		
		Employee emp = new Employee();
		emp = (Employee) session.getAttribute("Employee");
		Double reimbursementAmount = Double.parseDouble(request.getParameter("reimbursementAmount"));	
		//String requestDate = request.getParameter("requestDate");
		
		String description = request.getParameter("description");
		//receipt
		
		//int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		int reimbursementTypeId = Integer.parseInt(request.getParameter("inputReimbursementTypeId"));
		//int other = Integer.parseInt(request.getParameter("other"));
					
		RequestForm form = new RequestForm (reimbursementAmount, description, emp.getEmployee_id(), reimbursementTypeId);
		EmployeeRepository insertForm = new EmployeeRepository();
		
		try {
			 	insertForm.insertForm(form);
			 	//request.getRequestDispatcher("/successSubmit").forward(request, response);
					if(emp.getEmployee_role_id()==333) {
						//request.getRequestDispatcher("html/SubmitSuccess.html").forward(request, response);
					response.sendRedirect("html/SubmitSuccess.html");
					}else {
						//request.getRequestDispatcher("html/ManagerSubmitSuccess.html").forward(request, response);
						response.sendRedirect("html/ManagerSubmitSuccess.html");
					}
				
			
		} catch (DatabaseException | ParseException e) {
			
			e.printStackTrace();
			if(emp.getEmployee_role_id()==333) {
				request.getRequestDispatcher("html/FailSubmit.html").forward(request, response);
			//response.sendRedirect("html/FailSubmit.html");
			}else {
				request.getRequestDispatcher("html/ManagerFailSubmit.html").forward(request, response);
			}
		}
		
		/*PrintWriter write = response.getWriter();
		write.println("<html><body>");
		//write.println("Fail to submit Request Form!");
		write.println("reimbursment Amount: "+reimbursementAmount);
		write.println("reimbursment Date: "+requestDate);
		write.println("reimbursment description: "+ description);
		write.println("reimbursment type id: "+ reimbursementTypeId);
		write.println("employee id: "+ emp.getEmployee_id());
		write.println("</body></html>");*/
		//response.sendRedirect("html/FailSubmit.html");
		//response.sendRedirect("html/reimbursementform.html");
	}

}
