package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class DBConnection {
	 Connection conn;
	 Statement stmt;
	 ResultSet rs;
	public void connect() {
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getConstructor().newInstance();
			String url = "jdbc:sqlserver://localhost:1433;databaseName=JavaCK;user=sa;password=sab";
			 conn = DriverManager.getConnection(url); 
			System.out.println("Connected....");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public Connection connectSQL() {
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getConstructor().newInstance();
			String url = "jdbc:sqlserver://localhost:1433;databaseName=JavaCK;user=sa;password=sab";
			 conn = DriverManager.getConnection(url); 
			System.out.println("Connected....");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	public int executeDB(String sql) {
		int record=0;
		try {
			connect();
		    stmt = conn.createStatement();
		    record=stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return record;
		
	}
 // truy xuat csdl
	public ResultSet Account(String sql) {
		try {
			connect();
		    stmt = conn.createStatement();
		    rs= stmt.executeQuery(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				//conn.close();
				//stmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return rs;
		
	}
	
	
}
