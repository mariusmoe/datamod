package maalApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import javafx.scene.control.TextArea;
/**
 * This class writes to maal.
 * @author Eier
 *
 */
public class writeMaal {
	Connection connection; 
	Statement stmt;
	/**
	 * Establishes connection with db to write entries
	 */
	public writeMaal(){
		try{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag", "root", "eple");;
			this.stmt = connection.createStatement();
			
		}catch(Exception exc){
			System.out.println("Could not initiate connection. Try again");
			//exc.printStackTrace();
		}
	}
	/**
	 * Testmethod to retrieve existing row. Made to bugtest
	 * @param maal_id
	 * @return
	 */
	private static boolean exists(int maal_id){
		getMaal get = new getMaal();
		int amountOfColumns = get.getRows();
		System.out.println("The current amount of rows is: " + amountOfColumns);
		if (maal_id > amountOfColumns){
			return true;
		}
		return false;
	}
	
	/**
	 * This method is used by the controller to create a new maal-object in the DB
	 */
	public void createNewGoal(Maal maal){
		try {
			String createSqlEntry = "INSERT INTO maal (fra_dato, til_dato, oppnaadd_dato, maal) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(createSqlEntry);
			
			pstmt.setDate(1, Date.valueOf(maal.fraDato)); //Converts a util.Date instance into an sql.Date instance that can be understood by the prepared statement.
			pstmt.setDate(2, Date.valueOf(maal.tilDato));
			pstmt.setDate(3, maal.oppnaaddDato == null ? null : Date.valueOf(maal.oppnaaddDato));
			pstmt.setString(4, maal.maal);
			
			pstmt.executeUpdate();
			
			System.out.println("New goal successfully created");
			
		} catch (SQLException e) {
			System.out.println("Could not create a new goal. Try again");
			//e.printStackTrace();
		}
	}
	/**
	 * This method updates the selected goal
	 * @param maal
	 */
	public void updateGoal(Maal maal){
		PreparedStatement pstmt;
		try{
			
			String updateSqlEntry = "UPDATE maal (fra_dato, oppnaadd_dato, maal, til_dato) SET (?, ?, ?, ?) WHERE maal_id=?";
			
			pstmt = this.connection.prepareStatement(updateSqlEntry);
			
			pstmt.setDate(1, Date.valueOf(maal.fraDato));
			pstmt.setDate(2, maal.oppnaaddDato == null ? null : Date.valueOf(maal.oppnaaddDato));
			pstmt.setString(3, maal.maal);
			pstmt.setDate(4, Date.valueOf(maal.tilDato));
			pstmt.setInt(5, maal.id);
			
			
			System.out.println("Query: "+pstmt);
			
			//System.out.println(stmt.execute(updateSqlEntry));
			//(updateSqlEntry);
			//pstmt.executeUpdate();
			
			System.out.println("Goal "+maal.id+" updated");
			
			//pstmt.close();
			
		}catch(Exception exc){
			//System.out.println("Could not update goal, please try again: If the problem persists, contact admins");
			exc.printStackTrace();
		}
	}
	
	/**
	 * Deletes the selected goal from db.
	 * @param id
	 */
	public void deleteGoal(int id){
		try{
			String deleteSqlEntry = "DELETE from maal where maal_id=?";
			PreparedStatement pstmt = this.connection.prepareStatement(deleteSqlEntry);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("Målet er slettet");
		}catch(Exception exc){
			System.out.println("Goal could not be deleted");
		}
		
	}
	
}
