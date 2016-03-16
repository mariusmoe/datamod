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
 * Class to fetch data about exercises for a given training 
 * @author Marius Oscar Moe
 *
 */
public class ExerciseForTrainingFetch {

	private static Connection connect = null;
	private static Statement statement = null;
	// private ArrayList<LocalDateTime> timeList = new ArrayList<LocalDateTime>();
	private ArrayList<String> ovelseNavn = new ArrayList<String>();
	private ArrayList<Integer> belastning = new ArrayList<Integer>();
	private ArrayList<Integer> sett = new ArrayList<Integer>();
	private ArrayList<Integer> repetisjoner = new ArrayList<Integer>();
	private ArrayList<String> beskrivelse = new ArrayList<String>();
	private ArrayList<String> kategori = new ArrayList<String>();	//dos't have to be present
	private ArrayList<ArrayList> exForTraining = new ArrayList<ArrayList>();

	private ResultSet resultSet = null;
	
	/**
	 * Get database results for given training
	 * @param name training to look up
	 * @throws Exception Exceptions if connection fails
	 */
	public void readDataBase(String idtrening) throws Exception {
		System.out.println("trid to connect...");
		try {
			//mysql.stud.ntnu.no
			// connect = DriverManager.getConnection("jdbc:mysql://localhost/dag?" + "user=mariuom_koie&password=eple");
			// connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag" , "root", null);
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag","root","eple");
	     // Connection connection = DriverManager.getConnection("jdbc:mysql://129.241.160.113/mariuom_koiedb?" + "user=mariuom_koie&password=eple");

			statement = connect.createStatement();
	      // Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from dag.trening " +
					"inner join dag.ovelse_has_trening on trening.idtrening=ovelse_has_trening.trening_idtrening " +
					"inner join dag.ovelse on ovelse_has_trening.ovelse_navn=ovelse.navn " + 
					"where idtrening = " + Integer.parseInt(idtrening));
			
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
			//name = ((city.getName() == null) ? "N/A" : city.getName());
			ovelseNavn.add(resultSet.getString("ovelse_navn"));
			belastning.add(resultSet.getInt("belastning"));
			sett.add(resultSet.getInt("sett"));
			repetisjoner.add(resultSet.getInt("repetisjoner"));
			beskrivelse.add(resultSet.getString("beskrivelse"));
			kategori.add(resultSet.getString("kategori_knavn"));			
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
	
	
	public ArrayList<String> getOvelseNavn() {
		return ovelseNavn;
	}

	public ArrayList<Integer> getBelastning() {
		return belastning;
	}

	public ArrayList<Integer> getSett() {
		return sett;
	}

	public ArrayList<Integer> getRepetisjoner() {
		return repetisjoner;
	}

	public ArrayList<String> getBeskrivelse() {
		return beskrivelse;
	}

	public ArrayList<String> getKategori() {
		return kategori;
	}
	
	public ArrayList<ArrayList> getExForTraining() {
		exForTraining.add(ovelseNavn);
		exForTraining.add(belastning);
		exForTraining.add(sett);
		exForTraining.add(repetisjoner);
		exForTraining.add(beskrivelse);
		exForTraining.add(kategori);
		
		return exForTraining;
	}
}