import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.jdbc.CallableStatement;


public class CallableStmnt3 {

		public static void main(String[] args) throws SQLException {
			Connection myConn = null;
			java.sql.CallableStatement myStmnt = null;
			
			try{
				// get connection to db
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");
		
				String theDepartement = "Engineering";				
				
				//prepare procedure call
				myStmnt = myConn.prepareCall("{"
						+ "call get_count_for_department(?, ?)}");
				
				// 2. set parametes
				myStmnt.setString(1, theDepartement);
				myStmnt.registerOutParameter(2, Types.INTEGER);
				
				
				//call stored procedure
				System.out.println("\n calling stored procedure. get_count_for_department('" + theDepartement + "')");
				myStmnt.execute();
				System.out.println("\n finished procedure");
				
				int theCount = myStmnt.getInt(2);
				System.out.println("\n result = " + theCount);
								
				}
			catch(Exception exc){
				exc.printStackTrace();
			}
			finally{
				myConn.close();
			}
		}

	}
