import java.util.Scanner;

import org.sqlite.SQLiteDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;



public class MainData {
	
	public static String passwordCheck(String password2) {
		Scanner input = new Scanner(System.in);
		while(password2.length()< 8 && !password2.contains("<")) {
			System.out.println("Password Must be at least 8 Characters Try Again");
			password2 = input.next();
		}
		input.close();
	
		return password2;
	}
	
	public static SQLiteDataSource openDataBase(String info) {
		SQLiteDataSource ds = null;
		
		try {
		ds = new SQLiteDataSource();
		ds.setUrl(info);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		System.out.println( "Opened database successfully" );
		return ds;
	}
	
	public static void dataEnter(SQLiteDataSource ds, String user, String pass) {
		try ( Connection conn = ds.getConnection();) {
	    	PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `Data`(User, Password) VALUES (?, ?)");
	    	pstmt.setString(1, user);
	    	pstmt.setString(2, pass);
	    	pstmt.executeUpdate();
	        System.out.println( "Data Entered" );
	        //System TEST
	        
	    	
	    }catch(SQLException e) {
	        e.printStackTrace();
	        System.exit( 0 );
	    }
	}
	
}



