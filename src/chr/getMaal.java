package chr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * This class gets all information about maal.
 * @author Christian
 *
 */
public class getMaal {
	private static int amountOfColumns;
	private static int rowCount;
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData metadata;
	
	/**
	 * Constructor for getMaal
	 */
	public getMaal(){
		try{
			//Set up the connection to the database
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag", "root", "eple");
			this.stmt = con.createStatement();
			this.rs = stmt.executeQuery("select * from maal");
			this.metadata = rs.getMetaData();
			
			int rowCount = metadata.getColumnCount();
			amountOfColumns = rowCount;
			
			//Get all information about the goals
			retrieveMaal();
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	/**
	 * This method gets the amount of rows/entries in maal
	 * @return
	 */
	public List<Integer> retrieveIDs(){
		List<Integer> list = new ArrayList<>();
		try {
			ResultSet queryResult = stmt.executeQuery("select maal_id from maal");
			while (queryResult.next()){
				int maal_id = queryResult.getInt("maal_id");
				list.add(maal_id);
				System.out.println("Rownr: " + maal_id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Gets the amount of rows
	 * @return
	 */
	public int getRows(){
		List<Integer> allRows = retrieveIDs();
		int rows = allRows.size();
		return rows;
	}
	
	/**
	 * This method returns all maal-entries
	 */
	public void retrieveMaal(){
		try{
			while (rs.next()){
				rowCount += 1;
				String row = "";
				for (int i = 1; i <= amountOfColumns; i++){
					row += rs.getString(i) + ", ";
				}
				System.out.println(row);
			}
		}catch(Exception exc){
		exc.printStackTrace();
		}
	}
	
	public List<Object> getRow(int id){
		try {
			ResultSet queryResult = stmt.executeQuery("select * from maal where maal_id ='id' ");
 
			List<Object> row = new ArrayList<Object>();
			while(queryResult.next()){
				for(int i=1; i <= amountOfColumns; i++){
					row.add(queryResult.getString(i));
				}
			}
			System.out.println("The row consists of: " + row);
			return row;				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Close database connection
	 */
	private void close() {
		try {
			if (rs != null) {
				rs.close();
		    }
	
		    if (stmt != null) {
		        stmt.close();
		    }
	
		    if (con!= null) {
		    	con.close();
		    }
	    } catch (Exception e) {
	    }
	}

}
