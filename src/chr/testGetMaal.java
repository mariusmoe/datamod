package chr;


public class testGetMaal {
	public static void main(String[] args){
		try{
			System.out.println("Running...");
			getMaal testMaal = new getMaal();
			System.out.println("All ids in maal: " + testMaal.retrieveIDs());
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
}
