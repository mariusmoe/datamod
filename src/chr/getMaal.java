package chr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class getMaal {
	static int amountOfColumns;
	Statement myStmt;
	ResultSet myRs;
	ResultSetMetaData metadata;
	
	public getMaal(){
		try{
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag", "root1", "eple");
			Statement myStmt = myCon.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from maal");
			ResultSetMetaData metadata = myRs.getMetaData();
			
			int columnCount = metadata.getColumnCount();
			
			amountOfColumns = columnCount;
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	int getColumnCount(){
		System.out.println("Amount of columns: " + amountOfColumns);
		return amountOfColumns;
	}
	
	public void retrieveMaal(){
		try{
		while (myRs.next()){
			String row = "";
			for (int i = 1; i <= amountOfColumns; i++){
				row += myRs.getString(i) + ", ";
			}
			System.out.println(row);
		}
		}catch(Exception exc){
		exc.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
			System.out.println("Ran getMaal.java");
		try{
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag", "root1", "eple");
			Statement myStmt = myCon.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from maal");
			ResultSetMetaData metadata = myRs.getMetaData();
			
			int columnCount = metadata.getColumnCount();
			
			amountOfColumns = columnCount;
			
			while (myRs.next()){
				String row = "";
				for (int i = 1; i <= columnCount; i++){
					row += myRs.getString(i) + ", ";
				}
				System.out.println(row);
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}		
	}
}
