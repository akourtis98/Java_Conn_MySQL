import java.sql.DriverManager;
import java.sql.*;

public class JdbcTest {
	public static void main(String[] args) {
		
		Connection myConn = null;
		Statement myStmnt = null;
		ResultSet myRs = null;
		
		try{
			// get connection to db
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");
			
			System.out.println("Database connection successful! \n");
			// create a stmnt
			myStmnt = myConn.createStatement();
			
			//exec query
			myRs = myStmnt.executeQuery("select * from students");
			
			while(myRs.next()){
				System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
}
