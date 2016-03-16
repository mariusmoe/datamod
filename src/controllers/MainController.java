package controllers;

import db.ExerciseFetch;
import db.TrainingInsert;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import models.Exercise;
import models.SavedExercise;
import models.Workout;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainController {


	@FXML private DatePicker date;
	@FXML private ComboBox<String> hour;
	@FXML private ComboBox<String> minute;

	@FXML private TextField duration;

	@FXML private Slider feelingSlider;
	@FXML private Slider achievementSlider;


	@FXML private ToggleButton indoorToggle;
	@FXML private ToggleButton outdoorToggle;

	@FXML private Label airqText;
	@FXML private Slider airqSlider;
	@FXML private TextField specsNum;

	@FXML private TextField tempField;
	@FXML private TextArea weatherArea;

	@FXML private TextArea note;

	@FXML private ComboBox<String> exerciseComboBox;
	@FXML private Label exerciseNameLbl;
	@FXML private ToggleButton strengthToggle;
	@FXML private ToggleButton conditionToggle;

    @FXML private TextField noSets;
    @FXML private TextField noReps;
	@FXML private TextField weight;
	@FXML private Label kg;
	@FXML private TextField length;
	@FXML private Label min;

	@FXML void saveExercise() {
		int exLength;
		int exWeight;
		if (strengthToggle.selectedProperty().getValue()) {
			exLength = 0;
			exWeight = Integer.parseInt(weight.getText());

		}
		else {
			exWeight = 0;
			exLength = Integer.parseInt(length.getText());
		}

		SavedExercise svdExercise = new SavedExercise(exerciseNameLbl.getText(), Integer.parseInt(noSets.getText()), Integer.parseInt(noReps.getText()),exWeight, exLength);
		exerciseData.add(svdExercise);
    }

	@FXML private TableView savedExerciseTable;
	@FXML private TableColumn<SavedExercise, String> nameCol;
	@FXML private TableColumn<SavedExercise, Integer> setCol;
	@FXML private TableColumn<SavedExercise, Integer> repCol;
	@FXML private TableColumn<SavedExercise, Integer> weightCol;
	@FXML private TableColumn<SavedExercise, Integer> lengthCol;

	@FXML private Text savedText;

	/*Observable list of saved exercises that are to be displayed in tableview*/
	private final ObservableList<SavedExercise> exerciseData = FXCollections.observableArrayList();

	public ObservableList<SavedExercise> getExercises() {
		return exerciseData;
	}
	/*Collection of exercises that exist in the DB*/
	private Collection<Exercise> exercises;





	@FXML private void initialize(){


		conditionToggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue){
					conditionToggled();
					strengthToggle.setSelected(false);
				}
				else{
					strengthToggle.setSelected(true);

				}
			}
		});

		strengthToggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					strengthToggled();
					conditionToggle.setSelected(false);
				}
				else{
					conditionToggle.setSelected(true);
				}
			}
		});


		/*When indoorToggle is selected: run indoorToggled() and deselect outdoorToggle
		Else (meaning indoorToggle is deselected): select outdoorToggle */
		indoorToggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> property, Boolean oldValue, Boolean newValue){
				if (newValue) {
					indoorToggled();
					outdoorToggle.setSelected(false);
				}

				else {
					outdoorToggle.setSelected(true);
				}


			}

		});
		/*When outdoorToggle is selected: run outdoorToggled() and deselect indoorToggle
		Else (meaning outdoorToggle is deselected): select indoorToggle */
		outdoorToggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					outdoorToggled();
					indoorToggle.setSelected(false);
				}
				else
					indoorToggle.setSelected(true);
			}
		});
		fillComboBox();
		loadExercises();
		for (Exercise ex : exercises){

			exerciseComboBox.getItems().add(ex.getName());
		}
		exerciseComboBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				exerciseNameLbl.setText(newValue);

			}
		});



		/*Tells the table view what properties that should fill the individual columns*/
		nameCol.setCellValueFactory(cellData -> cellData.getValue().exNameProperty());
		setCol.setCellValueFactory(cellData -> cellData.getValue().exSetsProperty().asObject());
		repCol.setCellValueFactory(cellData -> cellData.getValue().exRepsProperty().asObject());
		weightCol.setCellValueFactory(cellData -> cellData.getValue().exWeightProperty().asObject());
		lengthCol.setCellValueFactory(cellData -> cellData.getValue().exLengthProperty().asObject());
		/*Tells tableview to get items from the observable list exerciseData*/
		savedExerciseTable.setItems(exerciseData);

		/*Ensures that at least one of each "toggle duo" is selected*/
		indoorToggle.setSelected(true);
		strengthToggle.setSelected(true);
	}

	private void strengthToggled() {
		strengthEnabler(true);
		conditionEnabler(false);
		}

	private void conditionEnabler(boolean enable) {
		if (enable){
			length.setDisable(false);
			min.setTextFill(Paint.valueOf("black"));
		}
		else{
			length.setDisable(true);
			min.setTextFill(Paint.valueOf("grey"));
		}
	}


	private void strengthEnabler(boolean enable) {
		if (enable){
			weight.setDisable(false);
			kg.setTextFill(Paint.valueOf("black"));
		}
		else {
			weight.setDisable(true);
			kg.setTextFill(Paint.valueOf("grey"));
		}
	}

	private void conditionToggled() {
		conditionEnabler(true);
		strengthEnabler(false);
	}

	void indoorToggled(){
		indoorEnabler(true);
		outdoorEnabler(false);

	}

	private void indoorEnabler(boolean enable) {
		if (enable){
			airqText.setTextFill(Paint.valueOf("black"));
			airqSlider.setDisable(false);
			specsNum.setDisable(false);
			}
		else{
			airqText.setTextFill(Paint.valueOf("grey"));
			airqSlider.setDisable(true);
			specsNum.setDisable(true);
		}
	}

	void outdoorToggled(){
		indoorEnabler(false);
		outdoorEnabler(true);

	}

	private void outdoorEnabler(boolean enabler) {
		if (enabler){
			tempField.setDisable(false);
			weatherArea.setDisable(false);

		}
		else {
			tempField.setDisable(true);
			weatherArea.setDisable(true);
		}
	}

	/*Fill the hour and minute combo boxes with values*/
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

	/*Instantiates ExerciseFetch, which gets exercises from DB and returns them in getExercises() as Collection<Exercise>*/
	public void loadExercises(){
		ExerciseFetch ef = new ExerciseFetch();
		ef.readExercises("1");
		exercises = ef.getExercises();

	}

	/*Runs when "Lagre trening" is pressed.*/
	@FXML void saveWorkout() {
		List<String> exList = new ArrayList<String>(exerciseData.size());
		for (SavedExercise svdEx : exerciseData){
			exList.add(svdEx.getName());
		}
		if(validInput()){
			LocalTime time = LocalTime.of(Integer.parseInt(hour.getValue()), Integer.parseInt(minute.getValue()));
			int feelSliderValue = (int) feelingSlider.getValue();
			int achievementSliderValue = (int) achievementSlider.getValue();
			int durationInt = Integer.parseInt(duration.getText());
			Workout work = new Workout(date.getValue(), time, durationInt, feelSliderValue, achievementSliderValue, note.getText());
			if (indoorToggle.selectedProperty().getValue()){
				int specsNumInt;
				if (specsNum.getText()==null) {
					specsNumInt = 0;
				}
				else{
					specsNumInt = Integer.parseInt(specsNum.getText());
				}
				work.setIndoorFields(specsNumInt, (int)airqSlider.getValue());
			}
			else{
				work.setOutdoorFields(Integer.parseInt(tempField.getText()), weatherArea.getText());
			}
			TrainingInsert trainIns = new TrainingInsert(work, indoorToggle.selectedProperty().getValue());
			trainIns.upload();
		}

	}
	/*Checks if the essential fields have values. The outcome results in appropriate feedback to the user*/
	private boolean validInput() {
		if (date.getValue()==null || duration.getText()==null || exerciseData.isEmpty()){
			savedText.setText("Treningen ble ikke lagret; sjekk at alle felt er gyldige og at minst én trening er lagt til");
			savedText.setFill(Paint.valueOf("red"));
			return false;
		}
		savedText.setText("Treningen ble lagret");
		savedText.setFill(Paint.valueOf("green"));
		return true;

	}


}
