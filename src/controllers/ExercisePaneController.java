package controllers;

import Model.SavedExercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ExercisePaneController {
	
	@FXML private TableView exerciseNameLbl;

    @FXML
    private TextField weight;

    @FXML
    private TextField noSets;

    @FXML
    private TextField noReps;


    private final ObservableList<SavedExercise> exercises = FXCollections.observableArrayList();

    public ObservableList<SavedExercise> getExercises() {
        return exercises;
    }
    @FXML
    void saveExercise() {
    	if (isValidInput()){
    		
    	}

    }

    public void initialize(){

    }

    
    boolean isValidInput(){
		return false;
    	
    	
    }



}
