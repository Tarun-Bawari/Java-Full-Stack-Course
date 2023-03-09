//rt.jar => jdk s/w
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Student {
	public void create(Connection connection, Statement statement, Scanner sc) throws SQLException {
		if (connection != null) {
	        System.out.println("Enter the sname:: ");
			String sname = sc.next();
			
			System.out.println("Enter the sage:: ");
			int sage = sc.nextInt();
			
			System.out.println("Enter the saddress:: ");
			String saddr = sc.next();
			
			//Step3: Creation of Statement Object(to move to the location using connection)
			statement =  connection.createStatement();
			if (statement != null) {
				//Step4: Using Statement Object execute the Query
				String sqlInsertQuery = String.format("insert into student(`sname`,`sage`,`saddr`) values('%s',%d,'%s')",sname,sage,saddr);
				System.out.println(sqlInsertQuery);
				int noOfRows = statement.executeUpdate(sqlInsertQuery);
				System.out.println("No of rows affected is :: " + noOfRows);
			}
		}	
	}
	public void read(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
		if (connection != null) {
			//Step3: Creation of Statement Object(to move to the location using connection)
			statement =  connection.createStatement();
			if (statement != null) {
				//Step4: Using Statement Object execute the Query
				String sqlSelectQuery = "select sid,sname,sage,saddr from student";
				resultSet = statement.executeQuery(sqlSelectQuery);
				if (resultSet != null) {
					//Step5: Process the resultSet ot get the data
					System.out.println("SID\tSNAME\tSAGE\tSADDR");
					while (resultSet.next()) {
						Integer id = resultSet.getInt("sid");
						String name = resultSet.getString("sname");
						Integer age = resultSet.getInt("sage");
						String address = resultSet.getString("saddr");
						System.out.println(id + "\t" + name + "\t" + age + "\t" + address);
					}
				}
			}
		}
	}
	public void update(Connection connection, Statement statement, Scanner sc) throws SQLException {
		if (connection != null) {
			String response = null;
	        int sid = 0;
	        int sage = 0;
	        String sname = null;
	        String saddr = null;
	        
	        System.out.println("Enter the Student ID of student which you want to update:: ");
			sid = sc.nextInt();
			
			System.out.println("Do you want to update Student Name? Type Y or N to proceed:: ");
			response = sc.next();
			
			while (true) {
				if (response.equalsIgnoreCase("Y")) {
					System.out.println("Enter the sname:: ");
					sname = sc.next();
					break;
				} else if(response.equalsIgnoreCase("N")) {
					break;
				} else {
					System.out.println("You Entered wrong value, type correct value :: ");
					response = sc.next();
				}
			}
			
			System.out.println("Do you want to update Student Age? Type Y or N to proceed:: ");
			response = sc.next();
			
			while (true) {
				if (response.equalsIgnoreCase("Y")) {
					System.out.println("Enter the sage:: ");
					sage = sc.nextInt();
					if (sage > 0) {
						break;
					} else {
						System.out.println("Age can't be 0 and negative");
					}
				} else if(response.equalsIgnoreCase("N")) {
					break;
				} else {
					System.out.println("You Entered wrong value, type correct value :: ");
					response = sc.next();
				}
			}
			
			System.out.println("Do you want to update Student Address? Type Yes or No to proceed:: ");
			response = sc.next();
			
			while (true) {
				if (response.equalsIgnoreCase("Y")) {
					System.out.println("Enter the saddress:: ");
					saddr = sc.next();
					break;
				} else if(response.equalsIgnoreCase("N")) {
					break;
				} else {
					System.out.println("You Entered wrong value, type correct value :: ");
					response = sc.next();
				}
			}
			
			//Step3: Creation of Statement Object(to move to the location using connection)
			statement =  connection.createStatement();
			if (statement != null) {
				//Step4: Using Statement Object execute the Query
				String sqlUpdateQuery = null;
				if (saddr != null & sage > 0 & sname != null) {
					sqlUpdateQuery = String.format("update student set sname = '%s', sage = %d, saddr = '%s' where sid = %d",sname,sage,saddr,sid);
				}else if (saddr != null & sname != null) {
					sqlUpdateQuery = String.format("update student set sname = '%s', saddr = '%s' where sid = %d",sname,saddr,sid);
				}else if (saddr != null & sage > 0) {
					sqlUpdateQuery = String.format("update student set sage = %d, saddr = '%s' where sid = %d",sage,saddr,sid);
				}else if (sage > 0 & sname != null) {
					sqlUpdateQuery = String.format("update student set sname = '%s', sage = %d where sid = %d",sname,sage,sid);
				}else if (saddr != null) {
					sqlUpdateQuery = String.format("update student set saddr = '%s' where sid = %d",saddr,sid);
				}else if (sage > 0) {
					sqlUpdateQuery = String.format("update student set sage = %d where sid = %d",sage,sid);
				}else if (sname != null) {
					sqlUpdateQuery = String.format("update student set sname = '%s' where sid = %d",sname,sid);
				}
				int noOfRows = statement.executeUpdate(sqlUpdateQuery);
				System.out.println("No of rows updated is :: " + noOfRows);
			}
		}
	}
	public void delete(Connection connection, Statement statement, Scanner sc) throws SQLException {
		if (connection != null) {
			System.out.println("Enter the Student ID of student which you want to delete:: ");
			int sid = sc.nextInt();
			//Step3: Creation of Statement Object(to move to the location using connection)
			statement =  connection.createStatement();
			if (statement != null) {
				//Step4: Using Statement Object execute the Query
				String sqlDeleteQuery = String.format("delete from student where sid = %d",sid);
				int noOfRows = statement.executeUpdate(sqlDeleteQuery);
				System.out.println("No of rows deleted is :: " + noOfRows);
			}
		}
	}
}

public class StudentCRUDDemo {
	public static void main(String[] args) throws SQLException {
		//resources used in jdbc application
		Scanner sc = new Scanner(System.in);
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		//Step2: Establish the connection
		String url="jdbc:mysql://localhost:3306/enterprisejavabatch";
		String username="root";
		String password="";
		
		try {
			System.out.println("Establishing the connection...");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established...");
			if (connection != null) {
				System.out.println("The below operations you can perform on the Student Table::");
				System.out.println("1.\tCREATE");
				System.out.println("2.\tREAD");
				System.out.println("3.\tUPDATE");
				System.out.println("4.\tDELETE");
				System.out.println("5.\tClose the Menu");
				System.out.println("Enter the operation you want to perform:: ");
				int choice = sc.nextInt();
				boolean startMenu = true;
				boolean wrongChoice = false;
				Student s = new Student();
				while(startMenu) {
					switch(choice) {
					case 1 : 
						wrongChoice = false;
					    s.create(connection,statement,sc);
					    s.read(connection,statement,resultSet);
						break;
					case 2:
						wrongChoice = false;
						s.read(connection,statement,resultSet);
						break;
					case 3:
						wrongChoice = false;
						s.update(connection,statement,sc);
						s.read(connection,statement,resultSet);
						break;
					case 4:
						wrongChoice = false;
						s.delete(connection,statement,sc);
						s.read(connection,statement,resultSet);
						break;
					case 5:
						wrongChoice = true;
						startMenu = false;
						break;
					default:
						wrongChoice = true;
						System.out.println("Wrong value entered for perform operation on Student Table, please enter value again:: ");
						choice = sc.nextInt();
						break;
					}
					if (!wrongChoice) {
						System.out.println("Do you want to perform more operation? Press Y or N to confirm:: ");
						String response = sc.next();
						
						while (true) {
							if (response.equalsIgnoreCase("Y")) {
								System.out.println("Enter the operation you want to perform:: ");
								choice = sc.nextInt();
								break;
							} else if(response.equalsIgnoreCase("N")) {
								startMenu = false;
								break;
							} else {
								System.out.println("You Entered wrong value, type correct value :: ");
								response = sc.next();
							}
						}
					}
				}
			}
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//Step6: closing the resources used
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				System.out.println("Closing the connection...");
				connection.close();
				System.out.println("Connection Closed...");
			}
			if (sc != null) {
				sc.close();
			}
		}
	}
}

