package in.ineuron.dynamicinput;

//rt.jar => jdk s/w
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JdbcUtil;

public class InsertApp {
	
	public static void main(String[] args) throws SQLException {
		//Resource used in Jdbc
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the sage:: ");
		int sage = sc.nextInt();
		
		System.out.print("Enter the sname:: ");
		String sname = sc.next();
		
		System.out.print("Enter the saddress:: ");
		String saddr = sc.next();
		
		String sqlInsertQuery = "insert into student(`sname`,`sage`,`saddr`) values(?,?,?)";
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null) 
				pstmt = connection.prepareStatement(sqlInsertQuery);
			
			if (pstmt != null) {
				
				pstmt.setString(1, sname);
				pstmt.setInt(2, sage);
				pstmt.setString(3, saddr);
				
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of rows Affected is :: " + rowAffected);
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

