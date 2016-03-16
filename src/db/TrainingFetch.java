package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * Class to fetch data about a training
 * @author Marius Oscar Moe
 *
 */
public class TrainingFetch {

	private static Connection connect = null;
	private static Statement statement = null;
	private ArrayList<LocalDateTime> timeList = new ArrayList<LocalDateTime>();
	private ArrayList<Integer> durationList = new ArrayList<Integer>();
	private ArrayList<Integer> personalFitList = new ArrayList<Integer>();
	private ArrayList<Integer> acchivementList = new ArrayList<Integer>();
	
	private HashMap<LocalDateTime, ArrayList> trainingMap = new HashMap<>();

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
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag","root1","eple");
	     // Connection connection = DriverManager.getConnection("jdbc:mysql://129.241.160.113/mariuom_koiedb?" + "user=mariuom_koie&password=eple");

			statement = connect.createStatement();
	      // Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from dag.trening inner join dag.maal on trening.maal_maal_id=maal.maal_id ");// where idtrening = '" + name + "'");
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

			ArrayList<Object> arr = new ArrayList<Object>();
			
			arr.add(resultSet.getInt("varighet"));
			arr.add(resultSet.getInt("personlig_form"));
			arr.add(resultSet.getInt("prestasjon"));
			arr.add(resultSet.getString("maal"));
			arr.add(resultSet.getInt("idtrening"));
			//add more columns from result-set here ->
			
			System.out.println(arr);
			
			trainingMap.put(resultSet.getTimestamp("tidspunkt").toLocalDateTime(), arr);
			
	    }
		
		System.out.println(trainingMap);
		
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
	 * @return the acchivementList
	 */
	public HashMap<LocalDateTime, ArrayList> getTrainingMap() {
		return trainingMap;
	}
	
}

