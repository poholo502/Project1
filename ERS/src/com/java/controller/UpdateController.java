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
import com.java.repository.EmployeeRepository;

@WebServlet("/update")
public class UpdateController extends HttpServlet{
	
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		//this is what i have in the first place
		if(session.getAttribute("Employee")==null) {
			request.getRequestDispatcher("/Login.html").forward(request, response);
			
		}
		
		Employee emp = (Employee) session.getAttribute("Employee");
		
		String newName = request.getParameter("nameChange");
		String newLastName = request.getParameter("lastNameChange");
		String newEmail =(String) request.getParameter("changeEmail");
		
		EmployeeRepository empRp = new EmployeeRepository();
		
		//1
		System.out.println(newEmail);
		if((newName != "") && (newLastName!= "") && (newEmail != "")) {
			//call updateName, updateLastName, updateEmail
			System.out.println(newName);
			System.out.println(newLastName);
			System.out.println(newEmail);
			try {
				empRp.updateFirstName(newName, emp.getEmployee_id());
				empRp.updateLastName(newLastName, emp.getEmployee_id());
				empRp.updateEmail(newEmail, emp.getEmployee_id());
				if(emp.getEmployee_role_id()==333) {
					System.out.println("inside if 333");
					//request.getRequestDispatcher("html/SubmitSuccess.html").forward(request, response);
				response.sendRedirect("html/SubmitSuccess.html");
				}else {
					//request.getRequestDispatcher("html/ManagerSubmitSuccess.html").forward(request, response);
					response.sendRedirect("html/ManagerSubmitSuccess.html");
				}
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(2);
			//2
		}else if(newName != "" && newLastName != "" && newEmail == "") {
			//call updateName and updateLastName
			try {
				empRp.updateFirstName(newName, emp.getEmployee_id());
				empRp.updateLastName(newLastName, emp.getEmployee_id());
				if(emp.getEmployee_role_id()==333) {
					//request.getRequestDispatcher("html/SubmitSuccess.html").forward(request, response);
				response.sendRedirect("html/SubmitSuccess.html");
				}else {
					//request.getRequestDispatcher("html/ManagerSubmitSuccess.html").forward(request, response);
					response.sendRedirect("html/ManagerSubmitSuccess.html");
				}
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//3
			System.out.println(3);
		}else if(newName != "" && newLastName=="" && newEmail== "") {
			//call updateName
			try {
				empRp.updateFirstName(newName, emp.getEmployee_id());
				if(emp.getEmployee_role_id()==333) {
					//request.getRequestDispatcher("html/SubmitSuccess.html").forward(request, response);
				response.sendRedirect("html/SubmitSuccess.html");
				}else {
					//request.getRequestDispatcher("html/ManagerSubmitSuccess.html").forward(request, response);
					response.sendRedirect("html/ManagerSubmitSuccess.html");
				}
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//4
			System.out.println(4);
		}else if(newName != "" && newLastName=="" && newEmail != "") {
			//call updateName and updateEmail
			try {
				empRp.updateFirstName(newName, emp.getEmployee_id());
				empRp.updateEmail(newEmail, emp.getEmployee_id());
				if(emp.getEmployee_role_id()==333) {
					//request.getRequestDispatcher("html/SubmitSuccess.html").forward(request, response);
				response.sendRedirect("html/SubmitSuccess.html");
				}else {
					//request.getRequestDispatcher("html/ManagerSubmitSuccess.html").forward(request, response);
					response.sendRedirect("html/ManagerSubmitSuccess.html");
				}
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//5
			System.out.println(5);
		}else if(newName =="" && newLastName!= "" && newEmail!="") {
			//call updateLastName, updateEmail
			try {
				empRp.updateLastName(newLastName, emp.getEmployee_id());
				empRp.updateEmail(newEmail, emp.getEmployee_id());
				if(emp.getEmployee_role_id()==333) {
					//request.getRequestDispatcher("html/SubmitSuccess.html").forward(request, response);
				response.sendRedirect("html/SubmitSuccess.html");
				}else {
					//request.getRequestDispatcher("html/ManagerSubmitSuccess.html").forward(request, response);
					response.sendRedirect("html/ManagerSubmitSuccess.html");
				}
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//6
			System.out.println(6);
		}else if(newName=="" && newLastName != "" && newEmail== "") {
			//call updateLastName
			try {
				empRp.updateLastName(newLastName, emp.getEmployee_id());
				if(emp.getEmployee_role_id()==333) {
					//request.getRequestDispatcher("html/SubmitSuccess.html").forward(request, response);
				response.sendRedirect("html/SubmitSuccess.html");
				}else {
					//request.getRequestDispatcher("html/ManagerSubmitSuccess.html").forward(request, response);
					response.sendRedirect("html/ManagerSubmitSuccess.html");
				}
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//7
			System.out.println(7);
		}else if(newName=="" && newLastName=="" && newEmail!=""){
			//call updateEmail
			try {
				empRp.updateEmail(newEmail, emp.getEmployee_id());
				if(emp.getEmployee_role_id()==333) {
					//request.getRequestDispatcher("html/SubmitSuccess.html").forward(request, response);
				response.sendRedirect("html/SubmitSuccess.html");
				}else {
					//request.getRequestDispatcher("html/ManagerSubmitSuccess.html").forward(request, response);
					response.sendRedirect("html/ManagerSubmitSuccess.html");
				}
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else {
			if(emp.getEmployee_role_id()==333) {
				//request.getRequestDispatcher("html/SubmitSuccess.html").forward(request, response);
			response.sendRedirect("html/FailSubmit.html");
			}else {
				//request.getRequestDispatcher("html/ManagerSubmitSuccess.html").forward(request, response);
				response.sendRedirect("html/ManagerFailSubmit.html");
			}
		}
		
	}
}
