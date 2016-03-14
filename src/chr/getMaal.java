package chr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class getMaal {
	private static int amountOfRows;
	private Statement myStmt;
	private ResultSet myRs;
	private ResultSetMetaData metadata;
	
	public getMaal(){
		try{
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag", "root1", "eple");
			Statement myStmt = myCon.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from maal");
			ResultSet ids = myStmt.executeQuery("select maal_id from maal");
			
			ResultSetMetaData metadata = myRs.getMetaData();
			ResultSetMetaData collectRows = ids.getMetaData();
			
			int rowCount = collectRows.getColumnCount();
			
			amountOfRows = rowCount;
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	public int getColumnCount(){
		System.out.println("Amount of columns: " + amountOfRows);
		return amountOfRows;
	}
	
	public void retrieveMaal(){
		try{
		while (myRs.next()){
			String row = "";
			for (int i = 1; i <= amountOfRows; i++){
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
			
			amountOfRows = columnCount;
			
			while (myRs.next()){
				String row = "";
				for (int i = 1; i <= amountOfRows; i++){
					row += myRs.getString(i) + ", ";
				}
				System.out.println(row);
				System.out.println(amountOfRows);
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}		
	}
}
