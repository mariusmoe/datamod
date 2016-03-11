package src.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ExercisePaneController {
	
	@FXML private ListView<String> exercises;

    @FXML
    private TextField weight;

    @FXML
    private TextField noSets;

    @FXML
    private TextField noReps;
    
    @FXML
    void saveExercise() {
    	if (isValidInput()){
    		
    	}

    }

    
    boolean isValidInput(){
		return false;
    	
    	
    }



}
