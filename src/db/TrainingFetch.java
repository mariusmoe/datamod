package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Class to fetch data about last dugnad
 * @author Marius Oscar Moe
 *
 */
public class TrainingFetch {

	private static Connection connect = null;
	private static Statement statement = null;
	private ArrayList<Integer> timeList = new ArrayList<Integer>();
	private ArrayList<Integer> durationList = new ArrayList<Integer>();
	private ArrayList<Date> personalFitList = new ArrayList<Date>();
	private ArrayList<Integer> acchivementList = new ArrayList<Integer>();

	private ResultSet resultSet = null;
	
	
	
	/**
	 * Get database results for given training
	 * @param name training to look up
	 * @throws Exception Exceptions if connection fails
	 */
	public void readDataBase(String name) throws Exception {
		
		try {
			//mysql.stud.ntnu.no
			// connect = DriverManager.getConnection("jdbc:mysql://localhost/dag?" + "user=mariuom_koie&password=eple");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/dag?", "user", "pass");
	     // Connection connection = DriverManager.getConnection("jdbc:mysql://129.241.160.113/mariuom_koiedb?" + "user=mariuom_koie&password=eple");

			statement = connect.createStatement();
	      // Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from dag.trening where idtrening = '" + name + "'");
			writeResultSet(resultSet);
	      
	    } catch (Exception e) {
	    	throw e;
	    } finally {
	    	close();
	    }
		
		
	}
	
	
	/**
	 * Process results from database
	 * @param resultSet Resultset from query
	 * @throws SQLException Errors with sql
	 */
	private void writeResultSet(ResultSet resultSet) throws SQLException {
		
		while (resultSet.next()) {
			
			timeList.add(resultSet.getTime("tidspunkt"));
			durationList.add(resultSet.getInt("varighet"));
			personalFitList.add(resultSet.getInt("personlig_form"));
			acchivementList.add(resultSet.getInt("prestasjon"));
	    }

	  }
	
	
	/**
	 * Close database connection
	 */
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
		    }
	
		    if (statement != null) {
		        statement.close();
		    }
	
		    if (connect != null) {
		    	connect.close();
		    }
	    } catch (Exception e) {
	    }
	}
	
	/**
	 * Get hashmap with dugnad values
	 * @return Dugnad Values
	 */
	public HashMap<String, Integer> getTrainingValueMap(){
		return trainingValues;
	}
	
}

