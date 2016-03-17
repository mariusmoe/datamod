package maalApp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class testGetMaal {
	
	
	public static void main(String[] args){
		try{
			getMaal testMaal = new getMaal();
			
			//MaalController controller = new MaalController();
			System.out.println("Running testGetMaal.java...");
			
			//System.out.println("All ids in maal: " + testMaal.retrieveIDs());
			//controller.fillMaal();
			//System.out.println(controller.getMaalList());
			//writeMaal goal = new writeMaal();
			Maal row = testMaal.getRow(2);
			System.out.println(row);
			
			//ArrayList<Maal> allMaal = testMaal.getAll();
			//System.out.println("AllMaal length: "+allMaal.size());
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
}
