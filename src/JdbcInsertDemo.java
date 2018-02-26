import java.sql.*;

public class JdbcInsertDemo {
	public static void main(String[] args) throws SQLException {
		
		Connection myConn = null;
		Statement myStmnt = null;
		ResultSet myRs = null;
		
		try{
			// get connection to db
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");

			// create a stmnt
			myStmnt = myConn.createStatement();
			
			//insert employee
			System.out.println("Inserting new employee");
			
			int rowsAffected = myStmnt.executeUpdate(
					"insert into students " + 
					"(last_name, first_name, email, department, salary) " +
					"values " + 
					"('Wright', 'Eric', 'eric.wright@foo.com', 'HR', 33000.00)");
			
			// verification by getting list of employees
			myRs = myStmnt.executeQuery("select * from students order by last_name");
			
			while(myRs.next()){
				System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
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
