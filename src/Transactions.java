import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;


public class Transactions {

		public static void main(String[] args) throws SQLException {
			Connection myConn = null;
			java.sql.Statement myStmnt = null;
			ResultSet myRs = null;
			
			try{
				// get connection to db
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "student", "student");
				//turn off autocommit
				myConn.setAutoCommit(false);
				//show salaries before
				System.out.println("Salaries before\n");
				myStmnt = myConn.createStatement();
				myRs = myStmnt.executeQuery("select * from employees where department = 'engineering'");
				while(myRs.next()){
					System.out.println(myRs.getString("last_name") + ", " + myRs.getString("salary"));
				}
				//delete all hr employees
				myStmnt.executeUpdate("delete from employees where department = 'HR'");
				
				
				//execute update
				myStmnt.executeUpdate("update employees set salary=300000 where department = 'engineering'");
				
				System.out.println("transactions steps are ready");
				
				//ask user if its okay
				boolean ok = askUserIfOkToSave();
				if(ok){
					myConn.commit();
					System.out.println("transaction commited");
					}else{
						myConn.rollback();;
						System.out.println("transaction rolled back");
					}
				//show salaries after
				myRs = myStmnt.executeQuery("select * from employees where department = 'engineering'");
				while(myRs.next()){
					System.out.println(myRs.getString("last_name") + ", " + myRs.getString("salary"));
				}
			}
			catch(Exception exc){
				exc.printStackTrace();
			}
			finally{
				myConn.close();
			}
		}
		
		public static boolean askUserIfOkToSave(){
			//ask
			 Scanner sc=new Scanner(System.in);  
			 System.out.println("Do you confirm transaction? Type 'yes' if you do."); 
			 String ans = sc.nextLine();
			 if (ans.equalsIgnoreCase("yes")){
				 return true;
			 }else{
				 return false;
			 }
			//if yes return true else false	
		}
	}
