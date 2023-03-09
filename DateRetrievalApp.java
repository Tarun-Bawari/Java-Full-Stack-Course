package in.ineuron.dynamicinput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JdbcUtil;

public class DateRetrievalApp {

	public static void main(String[] args) throws ParseException {
		
		//resource used
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the name:: ");
		String userName = sc.next();
		
		String sqlSelectQuery = "select name,address,gender,dob,doj,dom from user where name = ?";
		
		try {
			connection = JdbcUtil.getJdbcConnection();
			
			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);
			
			if (pstmt != null) {
				pstmt.setString(1, userName);
				resultSet = pstmt.executeQuery();
			}
			
			if (resultSet != null) {
				if (resultSet.next()) {
					String name = resultSet.getString(1);
					String address = resultSet.getString(2);
					String gender = resultSet.getString(3);
					java.sql.Date userDob = resultSet.getDate(4);
					java.sql.Date userDoj = resultSet.getDate(5);
					java.sql.Date dom = resultSet.getDate(6);
					System.out.println("SQLDate present in database for Dob is:: " + userDob);
					System.out.println("SQLDate present in database for Doj is:: " + userDoj);
					System.out.println("SQLDate present in database for Dom is:: " + dom);
					
					//formatting the output as the user choice(based on locale)
					SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
					String dob = sdf.format(userDob);
					
					sdf = new SimpleDateFormat("MM-dd-yyyy");
					String doj = sdf.format(userDoj);
					
					System.out.println("Dob after formatting in(dd-MM-yyyy) is:: " + dob);
					System.out.println("Doj after formatting in(MM-dd-yyyy) is:: " + doj);
					
					System.out.println("NAME\tADDRESS\tGENDER\tDOB\t\tDOJ\t\tDOM");
					
					System.out.println(name + "\t" + address + "\t" + gender + "\t" + dob + "\t" + doj + "\t" + dom);
				} else {
					System.out.println("Record not available for the given name: " + userName);
				}
			}
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.closeConnection(resultSet, pstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (sc != null) {
				sc.close();
			}
		}
	}
}
