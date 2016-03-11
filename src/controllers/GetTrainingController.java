package controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import db.TrainingFetch;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GetTrainingController {

    @FXML
    private TableColumn performanceColumn;

    @FXML
    private ChoiceBox trainingBox;

    @FXML
    private TableView trainingTable;

    @FXML
    private TableColumn goalColumn;

    @FXML
    private TableColumn dateColumn;

    @FXML
    private TableColumn durationColumn;

    @FXML
    private TableColumn howColumn;
    
    /**
	 * Initialize data
	 */
	@FXML
	public void initialize(){
		System.out.println("controller start!");
		TrainingFetch tf = new TrainingFetch();
		try {
			tf.readDataBase("1");
		} catch (Exception e) {
			
		}
		
		ArrayList<LocalDate> tTimeList = new ArrayList(tf.getTimeList());
		
		trainingTable.getColumns().addAll(k, lastNameCol, emailCol);
	}


}
