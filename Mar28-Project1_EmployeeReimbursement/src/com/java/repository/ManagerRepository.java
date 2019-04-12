package com.java.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.java.dto.Employee;
import com.java.dto.RequestForm;
import com.java.exception.DatabaseException;
import com.java.util.DBUtil;

public class ManagerRepository extends EmployeeRepository{
	ManagerRepository manager;
	
	//return ALL the employees
	public List<Employee> getAllEmployees() throws DatabaseException {											//return ALL the employees								
		String sql="select * from employees";
		try (Connection connection = DBUtil.getConnection();
				Statement st = connection.createStatement();) {
			ResultSet result = st.executeQuery(sql);
			
			List<Employee> db_employees = new ArrayList<>();
			
			while(result.next()) {
				Employee employee = extractEmployeeFromResultSet(result);
				db_employees.add(employee);
			}
			result.close();
			st.close();
			connection.close();
			return db_employees;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Unable to connect");
		}
	}
	
	//for manager to get see pending forms																
	public List<RequestForm> getPending(int employee_id) throws DatabaseException {						//for manager to get see pending forms	
		String sql = "select * from reimbursement where reimb_status_id = 77";
		try (Connection con = DBUtil.getConnection(); PreparedStatement pstm = con.prepareStatement(sql);) {

			ResultSet result = pstm.executeQuery();

			List<RequestForm> pending_forms = new ArrayList<>();

			while (result.next()) {
				RequestForm form = new RequestForm();
				pending_forms.add(form);
			}
			con.close();
			pstm.close();
			result.close();
			return pending_forms;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Unable to ");
		}
	}
	
	//for manager to get see All their approved forms
	public List<RequestForm> getApproved(int employee_id) throws DatabaseException {				//for manager to get see All their approved forms
		String sql = "select * from reimbursement where reimb_status_id = 88";
		try (Connection con = DBUtil.getConnection(); PreparedStatement pstm = con.prepareStatement(sql);) {

			ResultSet result = pstm.executeQuery();

			List<RequestForm> approved_forms = new ArrayList<>();

			while (result.next()) {
				RequestForm form = new RequestForm();
				approved_forms.add(form);
			}
			con.close();
			pstm.close();
			result.close();
			return approved_forms;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Unable to ");
		}
	}
	
	// approve/deny form
	public boolean approveFrom(int reimb_id) throws DatabaseException {
		String sql = "update reimbursement set reimb_staust_id=88 WHERE reimb_id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, reimb_id);

			int i = ps.executeUpdate();
			if (i == 1) {
				con.commit();
				con.close();
				ps.close();
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DatabaseException("Unablet to connect");
		}
		return false;
	}
	
	//return employee email
	public String returnEmail(int employee_id) throws DatabaseException {
		String sql = "select user_email from employees where ers_user_id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement pstm = con.prepareStatement(sql);) {

			pstm.setInt(1, employee_id);// passing the employee_id as input into '?'
			ResultSet result = pstm.executeQuery();
			
			if(result.next()) {
				String email = result.getString("user_email");
			
			con.close();
			pstm.close();
			result.close();
			return email;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Unable to ");
		}
		return null;
	}
	//remove main when done
	public static void main(String[] args) throws DatabaseException, SQLException {
		ManagerRepository get = new ManagerRepository();
		/*List list = new ArrayList<>();
		list = get.getAllEmployees();
		
		for(int i=0; i< list.size();i++) {
			System.out.println(list.get(i)+ "\n"); //testing to see if able to retreive all employees
		}*/
		
		/*boolean testUpdatePassword = get.updatePassword("newpassword", 200); //update password testing
		System.out.println(testUpdatePassword);*/
		
		/*String email = get.returnEmail(200); //get email testing
		System.out.println(email);*/
		
		//testing approveLogin
		Employee emp = new Employee();
		emp = get.approvingLogin("poholo502");
		System.out.println(emp);
	}
	
}
