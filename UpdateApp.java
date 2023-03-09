package in.ineuron.dynamicinput;

//rt.jar => jdk s/w
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JdbcUtil;

public class UpdateApp {
	
	public static void main(String[] args) throws SQLException {
		//Resource used in Jdbc
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the sid which you want to update:: ");
		int sid = sc.nextInt();
		
		System.out.print("Enter the sage:: ");
		int sage = sc.nextInt();
		
		System.out.print("Enter the sname:: ");
		String sname = sc.next();
		
		System.out.print("Enter the saddress:: ");
		String saddr = sc.next();
		
		String sqlUpdateQuery = "update student set sname=?, sage=?, saddr=? where sid=?";
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null) 
				pstmt = connection.prepareStatement(sqlUpdateQuery);
			
			if (pstmt != null) {
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddr);
				pstmt.setInt(4, sid);
				int rowUpdated = pstmt.executeUpdate();
				System.out.println("No of rows Updated is :: " + rowUpdated);
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

