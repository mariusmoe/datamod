package src.gui;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MainController {
	
	@FXML private VBox exContainer;
	@FXML private ComboBox<String> hour;
	@FXML private ComboBox<String> minute;
	
	@FXML private ExercisePaneController expController;
	@FXML private TextField weight;

    @FXML
    private TextField noSets;

    @FXML
    private TextField noReps;

	
	@FXML
    void saveExercise() {

    }
	
	public void initialize(){
		fillComboBox();
		/*ReadOnlyObjectProperty<Exercise> selectionProperty = exercises.getSelectionModel().selectedItemProperty();
		selectionProperty.addListener((property, oldValue, newValue) -> {
			expController.exercisePicked(newValue);
		}
		);*/
	}


	private void fillComboBox() {
		List<String> hours = new ArrayList<>(17);
		List<String> minutes = new ArrayList<>(13);
		for (int i=7;i<24;i++){
			hours.add(String.format("%02d", i));
		}
		hour.getItems().addAll(hours);
		hour.setValue(hours.get(0));
		for (int k=0; k < 60; k+=5){
			minutes.add(String.format("%02d", k));
		}
		minute.getItems().addAll(minutes);
		minute.setValue(minutes.get(0));
		
	}
	
}
