package in.ineuron.jdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	
	private JdbcUtil() {
		
	}
	
	public static Connection getJdbcConnection() throws SQLException {
		//resources used in jdbc application
		Connection connection = null;
		
		//Step2: Establish the connection
		String url="jdbc:mysql://localhost:3306/enterprisejavabatch";
		String username="root";
		String password="";
		
		connection = DriverManager.getConnection(url, username, password);
		
		if (connection != null) {
			return connection;
		}
		return connection;
	}
	
	public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {	
		//Step6: closing the resources used
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}
