import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.jdbc.CallableStatement;


public class StoredProcedureResultSet {

		public static void main(String[] args) throws SQLException {
			Connection myConn = null;
			java.sql.CallableStatement myStmnt = null;
			ResultSet myRs = null;
			
			try{
				// get connection to db
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");
		
				String theDepartement = "Engineering";				
				
				//prepare procedure call
				myStmnt = myConn.prepareCall("{"
						+ "call get_employees_for_department(?)}");
				
				// 2. set parametes
				myStmnt.setString(1, theDepartement);
				
				
				//call stored procedure
				System.out.println("\n calling stored procedure. get_employees_for_department('" + theDepartement + "')");
				myStmnt.execute();
				System.out.println("\n finished procedure");
				
				//get result set
				myRs = myStmnt.getResultSet();
				while(myRs.next()){
					System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name")+ ", " + myRs.getString("department"));
			
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
