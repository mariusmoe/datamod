package chr;



import java.time.LocalDate;
import java.util.List;


import chr.getMaal;
import chr.writeMaal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
/**
 * This is the controller for maal
 * @author Christian
 *
 */
public class MaalController{
	
	@FXML private ComboBox<String> maal_list;
	@FXML private DatePicker goalStart;
	@FXML private DatePicker goalEnd;
	@FXML private TextArea goalBox;
	@FXML private DatePicker goalDoneAt;
	@FXML private TextArea goalAchieved;
	@FXML private Button submitChanges;
	getMaal retrieve = new getMaal();
	writeMaal writeNew = new writeMaal();
	Maaladas maal = new Maaladas();
	
	/**
	 * This is the constructor for retrieving a goal from the database
	 * It will let the view be empty if no goal is selected.
	 * Once a goal is selected, all values will be retrieved and inserted into the corresponding boxes
	 */
	@FXML
	public void initialize(){
		goalBox.setText("");
		goalStart.setValue(null);
		goalEnd.setValue(null);
		goalDoneAt.setValue(null);
		goalAchieved.setVisible(false);
		
		
		if(maal_list.getItems() != null){ 
			maal_list.getItems().clear();
			}
		List<Integer> maalList = retrieve.retrieveIDs(); 
		for (int i=0; i <= maalList.size()-1; i++){
			String idToString = maalList.get(i).toString();
			maal_list.getItems().add(idToString);
		}
		System.out.println(maal_list);
	}
	
	/**
	 * This method retrieves a goal and inserts correct values
	 * @param maal_id
	 * parameter is the ID to the wished id to be retrieved from DB
	 */
	public void maalSelect(){
		int id = Integer.parseInt(maal_list.getValue());
		retrieve.getRow(id);
	}
	
	/**
	 * This method takes the selected ID in the dropdown menu and fills in from the DB
	 * @param ID
	 */
	public void fillMaal(int ID){
		Maal maal = retrieve.getRow(ID);
		
		if (maal == null) return;
		
		goalStart.setValue(maal.getLocalfraDato());
		goalDoneAt.setValue(maal.getLocaloppnaaddDato());
		goalBox.setText(maal.maal);
		goalEnd.setValue(maal.getLocaltilDato());
		
		if (goalDoneAt.getValue() != null){
			goalAchieved.setVisible(true);
		}
	}
	
	
	public void changeStart(){
		if(goalStart.getValue() == null) {
			goalStart.setStyle("-fx-border-color: red");
		}
		else{
			goalStart.setStyle("");
		}
	}
	
	public void changeEnd(){
		if(goalEnd.getValue() == null) {
			goalEnd.setStyle("-fx-border-color: red");
		}
		else{
			goalEnd.setStyle("");
		}
	}
	
	public void changeGoal(){
		if(goalBox.getText().equals("")){
			goalBox.setText("Must fill this box");
		}
	}
	
	public void changeAchieved(){
		if(goalDoneAt.getAccessibleText() != null){
			goalAchieved.setVisible(true);
		}
		goalAchieved.setVisible(false);
	}
	/**
	 * This method creates a new goal with what is inside each box after the createGoal-button is clicked.
	 */
	public void createGoal(){
		Maal newGoal = new Maal();
		
		newGoal.fraDato = goalStart.getValue();
		
		writeNew.createNewGoal(goalStart.getValue(), goalDoneAt.getValue(), goalBox.getText(), goalEnd.getValue());
	}

	public void submitChange(){
		try{
			//Update the existing maal-object with contents of all fx:ids
			int current = Integer.parseInt(maal_list.getValue());
			writeNew.updateGoal(current,goalStart.getValue(), goalDoneAt.getValue(), goalBox.getText(), goalEnd.getValue());
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
