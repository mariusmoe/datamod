package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


/**
 * Class to fetch data about last dugnad
 * @author Marius Oscar Moe
 *
 */
public class TrainingFetch {

	private static Connection connect = null;
	private static Statement statement = null;
	private ArrayList<Timestamp> timeList = new ArrayList<Timestamp>();
	private ArrayList<Integer> durationList = new ArrayList<Integer>();
	private ArrayList<Integer> personalFitList = new ArrayList<Integer>();
	private ArrayList<Integer> acchivementList = new ArrayList<Integer>();

	private ResultSet resultSet = null;
	
	/**
	 * Get database results for given training
	 * @param name training to look up
	 * @throws Exception Exceptions if connection fails
	 */
	public void readDataBase(String name) throws Exception {
		System.out.println("trid to connect...");
		try {
			//mysql.stud.ntnu.no
			// connect = DriverManager.getConnection("jdbc:mysql://localhost/dag?" + "user=mariuom_koie&password=eple");
			// connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag" , "root", null);
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag","root","eple");
	     // Connection connection = DriverManager.getConnection("jdbc:mysql://129.241.160.113/mariuom_koiedb?" + "user=mariuom_koie&password=eple");

			statement = connect.createStatement();
	      // Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from dag.trening"); //where idtrening = '" + name + "'");
			System.out.println("Connection SUCCESS - Querry SUCCESS");
			writeResultSet(resultSet);
	      
	    } catch (Exception e) {
	    	System.out.printf("failed" , e.toString());
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
			
			timeList.add(resultSet.getTimestamp("tidspunkt"));
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
	 * @return the timeList
	 */
	public ArrayList<Timestamp> getTimeList() {
		return timeList;
	}

	/**
	 * @return the durationList
	 */
	public ArrayList<Integer> getDurationList() {
		return durationList;
	}

	/**
	 * @return the personalFitList
	 */
	public ArrayList<Integer> getPersonalFitList() {
		return personalFitList;
	}

	/**
	 * @return the acchivementList
	 */
	public ArrayList<Integer> getAcchivementList() {
		return acchivementList;
	}
	
}

