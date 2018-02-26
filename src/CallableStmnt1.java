import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;


public class CallableStmnt1 {

		public static void main(String[] args) throws SQLException {
			Connection myConn = null;
			java.sql.CallableStatement myStmnt = null;
			
			try{
				// get connection to db
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");
		
				String theDepartement = "Engineering";
				int theIncreaseAmount = 10000;
				
				System.out.println("Salaries before \n");
				
				
				//prepare procedure call
				myStmnt = myConn.prepareCall("{"
						+ "call increase_salaries_for_department(?, ?)}");
				
				// 2. set parametes
				myStmnt.setString(1, theDepartement);
				myStmnt.setDouble(2, theIncreaseAmount);
				
				//call stored procedure
				System.out.println("\n calling stored procedure. increase_salaries_for_department('" + theDepartement + "', "+ theIncreaseAmount +")");
				myStmnt.executeQuery();
								
				}
			catch(Exception exc){
				exc.printStackTrace();
			}
			finally{
				myConn.close();
			}
		}

	}
