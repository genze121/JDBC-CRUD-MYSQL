package com.java.jdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * Author:- Tirtha Sharma
 * Position:- Full Stack Software Developer
 * JDK Version:- 1.8
 * JDBC Version:- 4.0
 */

public class JDBCCRUD {

	public static void main(String[] args) {

		DBUtil.getConnection();
		CRUD crud = new CRUD("CRUD", "CR001");
		CRUD.showMenu();

	}

}

class CRUD {

	static String user;
	static String userID;

	public CRUD(String user, String uid) {
		this.user = user;
		this.userID = uid;
	}

	// SQL Queries :- create , insert , update , delete , select

	// Creating the table
	private static final String createSQLTable = "create table emp(\r\n" + " eno int primary key not null,\r\n"
			+ " ename varchar(30),\r\n" + "salary int\r\n" + ");";

	// Inserting into the table
	private static final String insertSQLTable = "insert into emp(eno,ename,salary) values(?,?,?)";

	// Updating the table
	private static final String updateSQLTable = "update emp set ename=? where eno=?";

	// Deleting the table
	private static final String deleteSQLTable = "delete from emp where eno=?";

	// Reading the data from the table
	private static final String selectSQLTable = "select * from emp";

	public static void createTable() {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(createSQLTable);
			System.out.println(ps);
			int created = ps.executeUpdate();
			if (created == 1 && created > 0) {
				System.out.println("Table Created Successfully!!!");
			} else {
				System.out.println("Table Mismatched Problem Occured!!!");
			}

			DBUtil.closeResources_1(ps, con);

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	public static void insertTable() {

		Connection con = null;
		PreparedStatement ps = null;
		Scanner scan = new Scanner(System.in);
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(insertSQLTable);
			System.out.println(ps);
			while (true) {
				System.out.println("Enter the Employee Number:-");
				int eno = scan.nextInt();
				System.out.println("Enter the Employee Name:-");
				String ename = scan.next();
				System.out.println("Enter the Employee Salary:-");
				int esal = scan.nextInt();

				ps.setInt(1, eno);
				ps.setString(2, ename);
				ps.setInt(3, esal);

				int insertCount = ps.executeUpdate();

				if (insertCount == 1 || insertCount > 0) {
					System.out.println("Records are Inserted Successfully!!!");
				} else {
					System.out.println("Records are Mismatched!!!");
				}

				System.out.println("Total number of records inserted:- " + insertCount);

				System.out.println("Do you want to insert more records? Answer it in [Yes|No]");
				String choice = scan.next();
				if (choice.equalsIgnoreCase("NO")) {
					break;
				}
			}
			DBUtil.closeResources_1(ps, con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateTable() {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(updateSQLTable);
			System.out.println(ps);
			ps.setString(1, "Ram");
			ps.setInt(2, 1000);

			int updateCount = ps.executeUpdate();

			if (updateCount == 1 || updateCount > 0) {
				System.out.println("Records Updated Successfully!!");
			} else {
				System.out.println("Records Mismatched!!");
			}
			System.out.println("Total number of records updated:- " + updateCount);
			DBUtil.closeResources_1(ps, con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteTable() {

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(deleteSQLTable);
			System.out.println(ps);
			ps.setInt(1, 1003);
			int deletedCount = ps.executeUpdate();
			if (deletedCount == 1 || deletedCount > 0) {
				System.out.println("Records Deleted from the database!!!!");
			} else {
				System.out.println("Records Mismatched!!!");
			}
			System.out.println("The number of records deleted:- " + deletedCount);

			DBUtil.closeResources_1(ps, con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void selectTable() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(selectSQLTable);
			System.out.println(ps);
			boolean flag = false;
			System.out.println("---------------------------------------");
			System.out.println("ENO        ENAME        ESAL");
			System.out.println("---------------------------------------");
			result = ps.executeQuery();
			while (result.next()) {
				flag = true;
				int eno = result.getInt(1);
				String ename = result.getString(2);
				int esal = result.getInt(3);
				System.out.println(eno + " -> " + ename + " -> " + esal);

			}
			System.out.println("----------------------------------------");
			if (flag == false) {
				System.out.println("No matched records found from the database!!!");
			}
			DBUtil.closeResources_2(ps, result, con);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void showMenu() {

		int options;
		Scanner scan = new Scanner(System.in);

		System.out.println("Welcome:-" + user);
		System.out.println("Your ID is:- " + userID);
		System.out.println("You can perform your Database Operations according to your choice.....");
		System.out.println("\n");

		System.out.println("Let us choose the options accordingly to perform CRUD Operations!!");
		System.out.println("1. CREATING THE TABLE");
		System.out.println("2. INSERTING INTO THE TABLE");
		System.out.println("3. UPDATING INTO THE TABLE");
		System.out.println("4. DELETING INTO THE TABLE");
		System.out.println("5. READING FROM THE TABLE");
		System.out.println("6. EXITING THE CRUD APPLICATION");

		do {
			System.out.println("=======================================================");
			System.out.println("Enter the option you want to perform!!");
			options = scan.nextInt();
			System.out.println("=======================================================");

			switch (options) {
			case 1:
				System.out.println("===================================================");
				System.out.println("Creating the table!!!");
				CRUD.createTable();
				System.out.println("===================================================");
				break;

			case 2:
				System.out.println("===================================================");
				System.out.println("Inserting into the table!!!");
				CRUD.insertTable();
				System.out.println("===================================================");
				break;

			case 3:
				System.out.println("===================================================");
				System.out.println("Updating the table!!!");
				CRUD.updateTable();
				System.out.println("===================================================");
				break;

			case 4:
				System.out.println("===================================================");
				System.out.println("Deleting the table!!!");
				CRUD.deleteTable();
				System.out.println("===================================================");
				break;

			case 5:
				System.out.println("===================================================");
				System.out.println("Reading the data from the table!!!");
				CRUD.selectTable();
				System.out.println("===================================================");
				break;

			case 6:
				System.out.println("===================================================");
				System.out.println("Thank you visiting us.Have a nice day ahead!!");
				System.out.println("===================================================");
				break;

			default:
				System.out.println("Invalid option clicked.Please try again!!!");
				break;
			}
		} while (options != 6);

	}

}
