import java.sql.*;


public class JdbcPrepStmnts {

		public static void main(String[] args) throws SQLException {
			Connection myConn = null;
			PreparedStatement myStmnt = null;
			ResultSet myRs = null;
			
			try{
				// get connection to db
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");
		
				//prepare statement
				myStmnt = myConn.prepareStatement("select * from students where salary > ? and departement = ?");
		
				
				// 3. Set the parameters
				myStmnt.setDouble(1, 80000);
				myStmnt.setString(2, "Legal");
				
				// 4. Execute SQL query
				myRs = myStmnt.executeQuery();
				
				while(myRs.next()){
					System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name")+ ", " + myRs.getString("salary"));
				}
				
				}
			catch(Exception exc){
				exc.printStackTrace();
			}
			finally{
				if(myRs != null){
					myRs.close();
				}
			}
		}

	}
