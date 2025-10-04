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
