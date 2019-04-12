package com.java.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.java.exception.DatabaseException;

public class DBUtil {
	
	private static BasicDataSource ds = new BasicDataSource();	//need tomcat dependency
	//static Logger logger = Logger.getLogger(DBUtil.class); 		//need log4j dependency
	
	static {
		Properties property = new Properties();
		try {//getting info from data.properties driverName, url, username, password
			property.load(new FileInputStream("C:\\Users\\Poho\\Desktop\\Project1-v2\\ERS\\resources\\data.properties"));
			ds.setUsername(property.getProperty("username"));
			ds.setPassword(property.getProperty("password"));
			ds.setUrl(property.getProperty("url"));
			ds.setDriverClassName(property.getProperty("driverClassName"));
			ds.setDefaultAutoCommit(false); //we need to manually commit every time we make a connection
		}catch(IOException e){
			//logger.error("Unable to get connection"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws DatabaseException {
		try {
			return ds.getConnection();
		} catch(SQLException e) {
			//logger.error("Unable to get connection" + e.getMessage());
			e.printStackTrace();
			throw new DatabaseException(e.getMessage());
		}
	}

}
