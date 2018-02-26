import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUpdateDemo {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStmnt = null;
		ResultSet myRs = null;
		
		try{
			// get connection to db
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");

			// create a stmnt
			myStmnt = myConn.createStatement();
			
			//update the student
			System.out.println("\n Executing the update for: John Doe\n");
			myRs = myStmnt.executeQuery("select * from students where last_name = 'doe'");
			
			while(myRs.next()){
				System.out.println(myRs.getString("email") + "\n");
			}
			int rowsAffected = myStmnt.executeUpdate(
					"update students " + 
					"set email = 'john.doe@foo.com'" +
					"where last_name ='Doe' and first_name = 'John'"); 
			System.out.println("\n after the update:...\n");
			myRs = myStmnt.executeQuery("select * from students where last_name = 'doe'");
			
			while(myRs.next()){
				System.out.println(myRs.getString("email"));
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
