package com.njust.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/park","root","qazwsxedc");
			connection = DriverManager.getConnection("jdbc:mysql://192.168.43.246:3306/park", "root", "123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
}

	public static void main(String[] args){
		System.out.println(DBConnection.getConnection());
	}
}
