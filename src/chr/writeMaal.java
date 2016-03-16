package chr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * This class writes to maal.
 * @author Eier
 *
 */
public class writeMaal {
	Connection connection; 
	Statement stmt;
	
	public writeMaal(){
		try{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag", "root", "eple");;
			this.stmt = connection.createStatement();
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
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
	public void createNewGoal(LocalDate startDate, LocalDate achievedDate, String goal, LocalDate endDate){
		try {
		String createSqlEntry = 
				"insert into maal"
				+	" Values (null, startDate, achievedDate, goal, endDate)";
			Statement goalStmt = connection.createStatement();
			goalStmt.execute(createSqlEntry);
			
			System.out.println("New goal successfully created");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateGoal(int id,LocalDate startDate, LocalDate achievedDate, String goal, LocalDate endDate){
		try{
			String updateSqlEntry = "update maal set (id, startDate, achievedDate, goal, endDate) where id = maal_id ";
			stmt.execute(updateSqlEntry);
			System.out.println("Goal updated");
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		try{
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag", "root", "eple");
			
			Statement myStmt = myCon.createStatement();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter maal_id: ");
			int maal_id = scanner.nextInt();
			//String sql = "insert into maal"
			//		+ " (maal_id, fra_data, oppnaadd_dato, maal, til_dato)"
			//		+ " values ('5', '2016-07-20', '2016-08-10', 'Delta på triatlon', '2016-08-20')";
			
			if (!exists(maal_id)){
				//myStmt.executeUpdate(sql);
				System.out.println("maal_id exists, please consult programmers");
			}else{
				System.out.println("Insert complete");	
			}
			scanner.close();
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		
	}
}
