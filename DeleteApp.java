package in.ineuron.dynamicinput;

//rt.jar => jdk s/w
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JdbcUtil;

public class DeleteApp {
	
	public static void main(String[] args) throws SQLException {
		//Resource used in Jdbc
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the sid:: ");
		int sid = sc.nextInt();
		
		String sqlDeleteQuery = "delete from student where sid=?";
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null) 
				pstmt = connection.prepareStatement(sqlDeleteQuery);
			
			if (pstmt != null) {
				pstmt.setInt(1, sid);
				int rowDeleted = pstmt.executeUpdate();
				System.out.println("No of rows Deleted is :: " + rowDeleted);
			}
			
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeConnection(null, pstmt, connection);
			
			if (sc != null) {
				sc.close();
			}
		}
	}
}

