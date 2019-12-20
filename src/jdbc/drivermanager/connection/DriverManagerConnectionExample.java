package jdbc.drivermanager.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Schema
 mysql> describe Emp;
 +--------+-------------+------+-----+---------+-------+
 | Field  | Type        | Null | Key | Default | Extra |
 +--------+-------------+------+-----+---------+-------+
 | Name   | varchar(10) | YES  |     | NULL    |       |
 | Salary | int(11)     | YES  |     | NULL    |       |
 +--------+-------------+------+-----+---------+-------+
 2 rows in set (0.00 sec)

 mysql> */
public class DriverManagerConnectionExample {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://10.101.160.198:3306/Test", "root", "password");
			statement = connection.createStatement();
			rs = statement.executeQuery("Select * from Emp");
			while (rs.next()) {
				System.out.println(rs.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
