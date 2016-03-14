package chr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class writeMaal {
	
	private static boolean exists(int maal_id){
		getMaal get = new getMaal();
		int amountOfColumns = get.getColumnCount();
		System.out.println("The current amount of columns are: " + amountOfColumns);
		if (maal_id > amountOfColumns){
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		
		try{
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/dag", "root1", "eple");
			
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
