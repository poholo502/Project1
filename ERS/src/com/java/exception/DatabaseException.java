package com.java.exception;

public class DatabaseException extends Exception{
	public DatabaseException(String message) {
		super(message);
	}
	//we are using this exception class so we can throw and catch our own exception
	//it's good practice??
}
