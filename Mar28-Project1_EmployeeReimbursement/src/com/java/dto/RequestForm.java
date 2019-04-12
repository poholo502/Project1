package com.java.dto;

public class RequestForm {
	int reimb_id;
	double reimb_amount;
	String reimb_date;
	String resolved_date;
	String reimb_description;
	//image
	int reimb_author_id;
	int reimb_resolver_id;
	int reimb_status_id;
	int reimb_type_id;
	
	public RequestForm() {
		
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public double getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(double reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public String getReimb_date() {
		return reimb_date;
	}

	public void setReimb_date(String reimb_date) {
		this.reimb_date = reimb_date;
	}

	public String getResolved_date() {
		return resolved_date;
	}

	public void setResolved_date(String resolved_date) {
		this.resolved_date = resolved_date;
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public int getReimb_author_id() {
		return reimb_author_id;
	}

	public void setReimb_author_id(int reimb_author_id) {
		this.reimb_author_id = reimb_author_id;
	}

	public int getReimb_resolver_id() {
		return reimb_resolver_id;
	}

	public void setReimb_resolver_id(int reimb_resolver_id) {
		this.reimb_resolver_id = reimb_resolver_id;
	}

	public int getReimb_status_id() {
		return reimb_status_id;
	}

	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}

	public int getReimb_type_id() {
		return reimb_type_id;
	}

	public void setReimb_type_id(int reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}
	
	public String toString() {
		return "Reimbursement Id: " + reimb_id + "\nReimbursement Amount: $" + reimb_amount + "\nReimbusement Date: " + reimb_date + "\nResolved Date: " + resolved_date 
				+ "\nReimbursement Description: " + reimb_description + "\nReimbursement Author Id: "+ reimb_author_id +"\nReimbursement Resolver Id: " + reimb_resolver_id
				+ "\nReimbursement status id: " + reimb_status_id + "\nReimbursement Typed Id: " + reimb_type_id;
	}

	public RequestForm(int reimb_id, double reimb_amount, String reimb_date, String resolved_date,
			String reimb_description, int reimb_author_id, int reimb_resolver_id, int reimb_status_id,
			int reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_date = reimb_date;
		this.resolved_date = resolved_date;
		this.reimb_description = reimb_description;
		this.reimb_author_id = reimb_author_id;
		this.reimb_resolver_id = reimb_resolver_id;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}
}
