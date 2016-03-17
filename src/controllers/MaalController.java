package controllers;



import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import chr.getMaal;
import chr.Maal;
import chr.writeMaal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	@FXML private Button goalDelete;
	
	
	getMaal retrieve = new getMaal();
	writeMaal writeNew = new writeMaal();
	Maal maal = new Maal();
	List<Integer> maalList;
	
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
		goalDelete.setVisible(false);
		
		//This generates the goal-list
		if(maal_list.getItems() != null){
			try{
			maal_list.getItems().clear();
			//maal_list = new ComboBox<String>();
			}catch(Exception e){
				System.out.println("ASPODHYASOPDIASOIDHJPÅ");
			}
		}
		List<Integer> maalList = retrieve.retrieveIDs(); 
		for (int i=0; i <= maalList.size()-1; i++){
			String idToString = maalList.get(i).toString();
			maal_list.getItems().add(idToString);
		}
		
		goalDelete.textProperty().addListener(new ChangeListener<String>(){
			/**
			 * Makes the deletebutton appear/dissappear whether a goal has been selected or not
			 */
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				if (goalDelete.getText().length() != 0){
					goalDelete.setVisible(true);
					//sluttDato.setVisible(true);
				}
				else{
					goalDelete.setVisible(false);
				}
		}});

	}
	
	/**
	 * This method retrieves a goal and inserts correct values
	 * @param maal_id
	 * parameter is the ID to the wished id to be retrieved from DB
	 */
	public void maalSelect(){
		if (maal_list.getValue() != null){
			int id = Integer.parseInt(maal_list.getValue());
			retrieve.getRow(id);
			fillMaal(id);			
		}
	}
	
	/**
	 * This method takes the selected ID in the dropdown menu and fills in from the DB
	 * @param ID
	 */
	public void fillMaal(int ID){
		Maal maal = retrieve.getRow(ID);
		//System.out.println(maal);
		goalStart.setValue(maal.fraDato);
		goalDoneAt.setValue(maal.oppnaaddDato);
		goalBox.setText(maal.maal);
		goalEnd.setValue(maal.tilDato);
		
		goalDelete.setVisible(true);
		if (goalDoneAt.getValue() != null){
			goalAchieved.setVisible(true);
		}
	}
	
	/**
	 * Listens to changes in goalStartDate
	 */
	public void changeStart(){
		if(goalStart.getValue() == null) {
			goalStart.setPromptText("You need to fill this");
		}
		else{
			goalStart.setPromptText("Goal started at");
		}
	}
	
	/**
	 * Listens to changes in goalEndDate
	 */
	public void changeEnd(){
		if(goalEnd.getValue() == null) {
			goalEnd.setPromptText("You need to fill this");
		}
		else{
			goalEnd.setPromptText("Goal ends at");
		}
	}
	
	/**
	 * Listens to changes in goal
	 */
	public void changeGoal(){
		if(goalBox.getText().equals("")){
			goalBox.setText("Must fill this box");
		}
	}
	/**
	 * sets the notification window to visible if goal-date has been set
	 */
	public void changeAchieved(){
		if(goalDoneAt.getValue() == null){
			goalAchieved.setVisible(false);
		}
		goalAchieved.setVisible(true);
	}
	
	/**
	 * This method creates a new goal with what is inside each box after the createGoal-button is clicked.
	 */
	public void createGoal(){
		Maal newMaal = new Maal();
		
		newMaal.fraDato = goalStart.getValue();
		newMaal.tilDato = goalEnd.getValue();
		newMaal.oppnaaddDato = goalDoneAt.getValue();
		newMaal.maal = goalBox.getText();
		
		writeNew.createNewGoal(newMaal);
		initialize();
	}
	
	/**
	 * Calls the delete method in writeMaal and runs and update on the view
	 */
	public void onDelete(){
		//System.out.println("Size of list: " + maalList.size());
		int id = Integer.parseInt(maal_list.getValue());
		writeNew.deleteGoal(id);
		goalDelete.setVisible(false);
		initialize();
		
	}
	
	/**
	 * Allows you to update a sql entry
	 */
	public void submitChange(){
		try{
			//Update the existing maal-object with contents of all fx:ids
			int current = Integer.parseInt(maal_list.getValue());
			
			Maal newMaal = new Maal();
			
			newMaal.fraDato = goalStart.getValue();
			newMaal.tilDato = goalEnd.getValue();
			newMaal.oppnaaddDato = goalDoneAt.getValue();
			newMaal.maal = goalBox.getText();
			newMaal.id = current;
			
			writeNew.updateGoal(newMaal);
			
			//writeNew.updateGoal(current,goalStart.getValue(), goalDoneAt.getValue(), goalBox.getText(), goalEnd.getValue());
			/*changeStart();
			changeEnd();
			changeGoal();
			changeAchieved();*/
			
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
