package com.java.jdbc.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private static String driver;
	static {
		try {
			driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		String JDBC_URL = "jdbc:mysql://localhost:3306/crud";
		String USERNAME = "root";
		String PASSWORD = "root";
		Connection con = null;
		try {
			con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			if (con == null || con != null) {
				System.out.println("Connection is Established Successfully!!!");
			} else {
				System.out.println("Connection is not Established Successfully!!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closeResources_1(PreparedStatement ps, Connection con) {

		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
			System.out.println("Connection is Closed Successfully!!!");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void closeResources_2(PreparedStatement ps, ResultSet result, Connection con) {

		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (result != null && !result.isClosed()) {
				result.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
			System.out.println("Connection is Closed Successfully!!!");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
