package com.lab.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {

	public final static  Connection getDBconnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?"+e.getMessage());
			e.printStackTrace();
			
		}
		
		System.out.println("got it");
        Connection con = null;
        
        try {
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/labdb","root", "password");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/padmesh","root", "password");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return con; 

	}
}
