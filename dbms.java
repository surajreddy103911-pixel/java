package Sample;

import java.sql.*;


class MySQL_Connection1 {
	 String DB_URL = "jdbc:mysql://localhost:3306/rss";
 String USER = "root";
 String PASS = "root";

	public Connection myConnect() throws Exception {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connecting to the MySQL database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connection successful!");
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}
}

public class MySQL_2 {
	public static void main(String[] args) throws Exception {
		MySQL_Connection1 obj = new MySQL_Connection1();
		Connection conn = obj.myConnect();

		Statement stmt = conn.createStatement();

		// 🧩 1. Create table if not exists
		String createTable = "CREATE TABLE IF NOT EXISTS employees (" +
				"employee_id INT PRIMARY KEY, " +
				"first_name VARCHAR(50), " +
				"last_name VARCHAR(50))";
		stmt.executeUpdate(createTable);
		System.out.println("Table 'employees' created (if not already present).");

		// 🧩 2. Insert some sample data
		String insertData = "INSERT INTO employees (employee_id, first_name, last_name) VALUES " +
				"(101, 'John', 'Doe'), " +
				"(102, 'Alice', 'Smith'), " +
				"(103, 'Raj', 'Kumar') " +
				"ON DUPLICATE KEY UPDATE first_name=VALUES(first_name), last_name=VALUES(last_name)";
		stmt.executeUpdate(insertData);
		System.out.println("Sample data inserted.");

		// 🧩 3. Fetch data
		System.out.println("Fetching data from employees table...");
		String sql = "SELECT employee_id, first_name, last_name FROM employees";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int employee_id = rs.getInt("employee_id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");

			System.out.println("employee_id: " + employee_id + ", Name: " + first_name + " " + last_name);
		}

		if (conn != null) {
			conn.close();
			System.out.println("Connection closed.");
		}
	}
}









































package new1;

import java.sql.Statement;
import java.sql.*;
import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.DriverManager;


class yel{
	String url="jdbc:mysql://localhost:3306/rss";
	String name="root";
	String pass="root";
	
	public Connection myconnect() throws Exception{
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		conn=	DriverManager.getConnection(url,name,pass);
			
		}
		catch (Exception e) {
		System.out.println(e);
		}
		return conn;
		
	}

}
public class New {
	public static void main(String[] args) throws Exception{
		
	
	yel ob=new yel();
	Connection conn=ob.myconnect();
	Statement stmt=conn.createStatement();
	
	stmt.executeUpdate("drop table if exists emp");
	String table="create table emp (empid int,name varchar(20),dep varchar(20))";
	stmt.executeUpdate(table);
	
	String s="insert into emp values (123,'s','sdf'),(12,'sd','sd')";
	stmt.executeUpdate(s);
	
	ResultSet rs =stmt.executeQuery("select*from emp");
	
	while(rs.next()) {
		System.out.println(rs.getInt("empid")+rs.getString("name"));
		
		System.out.println(rs.getString("dep"));

	}
	rs=stmt.executeQuery("select empid from emp");
	while(rs.next()) {
		System.out.println(rs.getInt("empid"));
	}
	
	
	
	
	
	
	
	}
}

