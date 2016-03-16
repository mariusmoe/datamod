package chr;

import java.awt.Button;
import java.awt.TextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import gui.ExercisePaneController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
/**
 * This is the controller for maal
 * @author Christian
 *
 */
public class MaalController implements javafx.fxml.Initializable{
	
	@FXML private ComboBox<String> maal_list;
	@FXML private DatePicker goalStart;
	@FXML private DatePicker goalEnd;
	@FXML private TextArea goalBox;
	@FXML private DatePicker goalDoneAt;
	@FXML private TextArea goalAchieved;
	@FXML private Button submitChanges;
	
	/**
	 * This is the constructor for retrieving a goal from the database
	 * It will let the view be empty if no goal is selected.
	 * Once a goal is selected, all values will be retrieved and inserted into the corresponding boxes
	 */
	public void initialize(URL location, ResourceBundle resources){
		Maal maal = new Maal();
	}
	public MaalController(){
		goalAchieved.setVisible(false);
	}
	
	/**
	 * This method retrieves a goal and inserts correct values
	 * @param maal_id
	 * parameter is the ID to the wished id to be retrieved from DB
	 */
	public void retrieveMaal(int maal_id){
		
	}
	
	public void fillMaal(){
		ArrayList<Integer> maal = new ArrayList<>();
		ArrayList<Integer> maal = getMaal().retrieveIds();
		maal_list.getItems().addAll(arg0)
	}
	
	
	private void changeStart(){
		
	}
	
	private void changeEnd(){
		
	}
	
	private void changeGoal(){
		
	}
	
	private void changeAchieved(){
		if(goalDoneAt.getAccessibleText() != null){
			goalAchieved.setVisible(true);
		}
		goalAchieved.setVisible(false);
	}
	
	private void submitChange(){
		try{
			fillMaal();
			changeStart();
			changeEnd();
			changeGoal();
			changeAchieved();
			
		}
		catch (Exception e){
			
		}
	}
}
