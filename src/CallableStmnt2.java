import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.jdbc.CallableStatement;


public class CallableStmnt2 {

		public static void main(String[] args) throws SQLException {
			Connection myConn = null;
			java.sql.CallableStatement myStmnt = null;
			
			try{
				// get connection to db
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");
		
				String theDepartement = "Engineering";				
				
				//prepare procedure call
				myStmnt = myConn.prepareCall("{"
						+ "call increase_salaries_for_department(?)}");
				
				// 2. set parametes
				myStmnt.registerOutParameter(1, Types.VARCHAR);
				myStmnt.setString(1, theDepartement);
				
				//call stored procedure
				System.out.println("\n calling stored procedure. greet_the_department('" + theDepartement + "')");
				myStmnt.execute();
				System.out.println("\n finished procedure");
				
				String theResult = myStmnt.getString(1);
				System.out.println("\n result = " + theResult);
								
				}
			catch(Exception exc){
				exc.printStackTrace();
			}
			finally{
				myConn.close();
			}
		}

	}
