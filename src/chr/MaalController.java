package chr;

import java.awt.Button;
import java.awt.TextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
	getMaal retrieve = new getMaal();
	
	/**
	 * This is the constructor for retrieving a goal from the database
	 * It will let the view be empty if no goal is selected.
	 * Once a goal is selected, all values will be retrieved and inserted into the corresponding boxes
	 */
	public void initialize(URL location, ResourceBundle resources){
		Maal maal = new Maal();
		goalAchieved.setVisible(false);
		if(maal_list.getItems() != null){ 
			maal_list.getItems().clear();
			}
		List<Integer> maal = retrieve.retrieveIDs(); 
		for (int i=1; i <= maal.size(); i++){
			String idToString = maal.get(i).toString();
			maal_list.getItems().add(idToString);
		}
		System.out.println(maal_list);
	}
	
	/**
	 * This method retrieves a goal and inserts correct values
	 * @param maal_id
	 * parameter is the ID to the wished id to be retrieved from DB
	 */
	public void retrieveMaal(int maal_id){
		
	}
	
	public void fillMaal(){
		
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
	/**
	 *Returns maal_list 
	 * @return
	 */
	public ComboBox<String> getMaalList(){
		return maal_list;
	}
}
