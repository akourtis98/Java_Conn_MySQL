import java.sql.*;

public class ResultMetaData {

	
		public static void main(String[] args) throws SQLException {
			Connection myConn = null;
			ResultSet myRs = null;
			Statement myStmnt = null;
			
			try{
				// get connection to db
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");
		
				//run query
				myStmnt = myConn.createStatement();
				myRs = myStmnt.executeQuery("select id, last_name, first_name, salary from employees");
				
				//get result set metadata
				
				ResultSetMetaData rsMetaData = myRs.getMetaData();
				
				//display info
				int columnCount = rsMetaData.getColumnCount();
				System.out.println("column count: " + columnCount);
				
				for (int column=1; column <= columnCount; column++){
					System.out.println("column name: " + rsMetaData.getColumnName(column));
					System.out.println("column version: " + rsMetaData.getColumnTypeName(column));
					System.out.println("Is Nullable version: " + rsMetaData.isNullable(column));
					System.out.println("Is auto increment version: " + rsMetaData.isAutoIncrement(column) + "\n");
					}
				}
			catch(Exception exc){
				exc.printStackTrace();
			}
			finally{
				myConn.close();
			}
		}

	}
