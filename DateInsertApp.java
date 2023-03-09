package in.ineuron.dynamicinput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JdbcUtil;

public class DateInsertApp {

	public static void main(String[] args) throws ParseException {
		
		//resource used
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the name:: ");
		String name = sc.next();
		
		System.out.print("Enter the address:: ");
		String address = sc.next();
		
		System.out.print("Enter the gender:: ");
		String gender = sc.next();
		
		System.out.print("Enter the dob::(dd-MM-yyyy) ");
		String sdob = sc.next();
		
		System.out.print("Enter the doj::(MM-dd-yyyy) ");
		String sdoj = sc.next();
		
		System.out.print("Enter the dom::(yyyy-MM-dd) ");
		String sdom = sc.next();
		
		SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date uDate = sdf.parse(sdob);
		
		long time = uDate.getTime();
		java.sql.Date sqlDateDob = new java.sql.Date(time);
		
		System.out.println("String dob is::" + sdob);
		System.out.println("Util date is ::" + uDate);
		System.out.println("Sql  date is ::" + sqlDateDob);
		
		sdf =  new SimpleDateFormat("MM-dd-yyyy");
		uDate = sdf.parse(sdoj);
		
		time = uDate.getTime();
		java.sql.Date sqlDateDoj = new java.sql.Date(time);
		
		System.out.println("String dob is::" + sdoj);
		System.out.println("Util date is ::" + uDate);
		System.out.println("Sql  date is ::" + sqlDateDoj);
		
		java.sql.Date sqlDateDom = java.sql.Date.valueOf(sdom);
		
		System.out.println("String dob is::" + sdom);
		System.out.println("Sql  date is ::" + sqlDateDom);
		
		String sqlInsertQuery = "insert into user(`name`,`address`,`gender`,`dob`,`doj`,`dom`) values(?,?,?,?,?,?)";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if (connection != null)
				pstmt = connection.prepareStatement(sqlInsertQuery);
			
			if (pstmt != null) {
				pstmt.setString(1, name);
				pstmt.setString(2, address);
				pstmt.setString(3, gender);
				pstmt.setDate(4, sqlDateDob);
				pstmt.setDate(5, sqlDateDoj);
				pstmt.setDate(6, sqlDateDom);
				int rowAffected = pstmt.executeUpdate();
				System.out.println("No of rows Affeceted is:: " + rowAffected);
			}
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConnection(null, pstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (sc != null) {
				sc.close();
			}
		}
	}
}

