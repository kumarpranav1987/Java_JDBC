package jdbc.datasource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;

public class ApacheCommonsConnectionPooling {
	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = DBCPDataSource.getConncetion();
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

class DBCPDataSource {
	private static BasicDataSource ds = new BasicDataSource();
	static {
		ds.setUrl("jdbc:mysql://10.101.160.198:3306/Test");
		ds.setUsername("root");
		ds.setPassword("password");
		ds.setMinIdle(5);
		ds.setMaxIdle(10);
		ds.setMaxOpenPreparedStatements(100);
	}

	public static final Connection getConncetion() throws SQLException {
		return ds.getConnection();
	}
}