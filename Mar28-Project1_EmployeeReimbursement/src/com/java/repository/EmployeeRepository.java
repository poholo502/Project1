package com.java.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.dto.Employee;
import com.java.dto.RequestForm;
import com.java.exception.DatabaseException;
import com.java.util.DBUtil;

public class EmployeeRepository {
	
	//method to extract employee info from ResultSet
	protected Employee extractEmployeeFromResultSet(ResultSet result) throws SQLException {
		Employee employee = new Employee();
		
		employee.setEmployee_id(result.getInt("ers_user_id"));
		employee.setUsername(result.getString("ers_username"));
		employee.setPassword(result.getString("ers_password"));
		employee.setFirstName(result.getString("user_first_name"));
		employee.setLastName(result.getString("user_last_name"));
		employee.setEmail(result.getString("user_email"));
		employee.setEmployee_role_id(result.getInt("user_role_id"));
		
		return employee;
	}
	
	//method to extract form info from ResultSet
	protected RequestForm extractFormFromResultSet(ResultSet result) throws SQLException {
		RequestForm form = new RequestForm();
		
		form.setReimb_id(result.getInt("reimb_id"));
		form.setReimb_amount(result.getDouble("reimb_amount"));
		form.setReimb_date(result.getString("reimb_submitted"));
		form.setResolved_date(result.getString("reimb_resolved"));
		form.setReimb_description(result.getString("reimb_description"));
		//image
		form.setReimb_author_id(result.getInt("reimb_author"));
		form.setReimb_resolver_id(result.getInt("reimb_resolver"));
		form.setReimb_status_id(result.getInt("reimb_status_id"));
		form.setReimb_type_id(result.getInt("reimb_type_id"));
		
		return form;
	}
	
	//for login
	public Employee approvingLogin(String passInusername)
			throws SQLException, DatabaseException {

		String sql = "SELECT * FROM employees where ers_username = ?";
		Employee employee = null;
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstm = connection.prepareStatement(sql);) {

			pstm.setString(1, passInusername);
			ResultSet result = pstm.executeQuery();

			while(result.next()) {
				employee = new Employee(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getInt(7));
			}
			result.close();
			pstm.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseException("Unable to connect");
		}
		return employee;
	}
	
	//for to get 1 specific employee info from the database by providing id
	public Employee getEmployeeInfo(int employee_id) throws DatabaseException {
		String sql = "select * from employees where id = ?";
		try(Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql); ){
			
			pstm.setInt(1, employee_id);//passing the employee_id as input into '?'
			ResultSet result = pstm.executeQuery();
			
			if(result.next()) {
				return extractEmployeeFromResultSet(result);
			}
			con.close();
			pstm.close();
			result.close();
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DatabaseException("Unable to ");
			}
		return null;
	}
	
	//for employee to get see All their pending forms
	public List<RequestForm> getPending(int employee_id) throws DatabaseException{
		String sql = "select * from reimbursement where reimb_status_id = 77 and id = ?";
		try(Connection con = DBUtil.getConnection();
			PreparedStatement pstm = con.prepareStatement(sql); ){
			
			pstm.setInt(1, employee_id);//passing the employee_id as input into '?'
			ResultSet result = pstm.executeQuery();
			
			List<RequestForm> pending_forms = new ArrayList<>();
			
			while(result.next()) {
				RequestForm form = new RequestForm();
				pending_forms.add(form);
			}
			con.close();
			pstm.close();
			result.close();
			return pending_forms;
			
			}catch (SQLException e) {
				e.printStackTrace();
				throw new DatabaseException("Unable to ");
			}
	}
	
	//for employee to get see All their pending forms
	public List<RequestForm> getApproved(int employee_id) throws DatabaseException {
		String sql = "select * from reimbursement where reimb_status_id = 88 and id = ?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement pstm = con.prepareStatement(sql);) {

			pstm.setInt(1, employee_id);// passing the employee_id as input into '?'
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
			throw new DatabaseException("Unable to connect");
		}
	}
	
	//for employee to update username, password, first name, last name and email, role_id all at once
	public boolean updateAll(Employee employee) throws DatabaseException {
	    String sql = "update employees set ers_username=?, ers_password=?, user_first_name=?, user_last_name=?, user_email=?, user_role_id=? WHERE ers_user_id=?";
	    try (Connection con = DBUtil.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);){
	        
	        //ps.setInt(1, employee.getEmployee_id());
	        ps.setString(1, employee.getUsername());
	        ps.setString(2, employee.getPassword());
	        ps.setString(3, employee.getFirstName());
	        ps.setString(4, employee.getLastName());
	        ps.setString(5, employee.getEmail());
	    
	        int i = ps.executeUpdate();
	      if(i == 1) {
	    return true;
	      }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        throw new DatabaseException("Unablet to connect");
	    }
	    return false;
	}
	
	//for employee to update password
	public boolean updatePassword(String newPassword, int employee_id) throws DatabaseException {
	    String sql = "update employees set ers_password=? WHERE ers_user_id=?";
	    try (Connection con = DBUtil.getConnection();
	        PreparedStatement ps = con.prepareStatement(sql);){
	      
	        ps.setString(1, newPassword);
	        ps.setInt(2, employee_id);
	        
	        int i = ps.executeUpdate();
	      if(i == 1) {
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
	//for employee to update username
	public boolean updateUsername(String newUsername, int employee_id) throws DatabaseException {
		String sql = "update employees set ers_username=? WHERE ers_user_id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, newUsername);
			ps.setInt(2, employee_id);

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
	
	//for employee to update first name
	public boolean updateFirstName(String newFirstName, int employee_id) throws DatabaseException {
		String sql = "update employees set user_first_name=? WHERE ers_user_id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, newFirstName);
			ps.setInt(2, employee_id);

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
		
		//for employee to update last name
	public boolean updateLastName(String newLastName, int employee_id) throws DatabaseException {
		String sql = "update employees set user_last_name=? WHERE ers_user_id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, newLastName);
			ps.setInt(2, employee_id);

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
	
	//for employee to update email
	public boolean updateEmail(String newEmail, int employee_id) throws DatabaseException {
		String sql = "update employees set user_email=? WHERE ers_user_id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, newEmail);
			ps.setInt(2, employee_id);

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
	
	//for employee to update User Id role, just if they got promoted to be a manager
	public boolean updateUserIdRole(String newIdRole, int employee_id) throws DatabaseException {
		String sql = "update employees set user_role_id=? WHERE ers_user_id=?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, newIdRole);
			ps.setInt(2, employee_id);

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
	
	//insert info to request form //have to modify, receipt is an image
	public boolean insertForm(RequestForm form) throws DatabaseException {
		String sql = "insert into reimbursement values "
				+ "(reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, form.getReimb_id());
			ps.setDouble(2, form.getReimb_amount());
			ps.setString(3, form.getReimb_date());
			ps.setString(4, form.getResolved_date());
			ps.setString(5, form.getReimb_description());
			ps.setString(6, " "); // receipt, suppose to be an image
			ps.setInt(7, form.getReimb_author_id());
			ps.setInt(8, form.getReimb_resolver_id());
			ps.setInt(9, form.getReimb_status_id());
			ps.setInt(10, form.getReimb_type_id());

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
}
