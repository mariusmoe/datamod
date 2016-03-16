package chr;


public class testGetMaal {
	
	
	public static void main(String[] args){
		try{
			//getMaal testMaal = new getMaal();
			MaalController controller = new MaalController();
			System.out.println("Running...");
			
			//System.out.println("All ids in maal: " + testMaal.retrieveIDs());
			controller.fillMaal();
			System.out.println(controller.getMaalList());
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
}
