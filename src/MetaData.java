import java.sql.*;

public class MetaData {

	
		public static void main(String[] args) throws SQLException {
			Connection myConn = null;
			
			try{
				// get connection to db
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");
		
				//get metadata
				DatabaseMetaData databseMetaData = myConn.getMetaData();
				
				System.out.println("product name: " + databseMetaData.getDatabaseProductName());
				System.out.println("product version: " + databseMetaData.getDatabaseProductVersion());
				System.out.println();	
				
				System.out.println("JDBC driver name: " + databseMetaData.getDriverName());
				System.out.println("JDBC driver version: " + databseMetaData.getDriverVersion());
				}
			catch(Exception exc){
				exc.printStackTrace();
			}
			finally{
				myConn.close();
			}
		}

	}
