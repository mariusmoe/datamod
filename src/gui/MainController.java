package gui;
import db.ExerciseFetch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainController {




	@FXML private Label airqText;
	@FXML private Slider airqSlider;
	@FXML private TextField specsNum;

	@FXML private TextField tempField;
	@FXML private TextArea weatherArea;

	@FXML private ToggleButton indoorToggle;
	@FXML private ToggleButton outdoorToggle;

	@FXML private VBox exContainer;
	@FXML private ComboBox<String> hour;
	@FXML private ComboBox<String> minute;
	
	@FXML private ExercisePaneController expController;
	@FXML private TextField weight;

    @FXML private TextField noSets;

    @FXML private TextField noReps;
	@FXML private ComboBox<String> exerciseComboBox;

	private Collection<Exercise> exercises;
	
	@FXML
    void saveExercise() {

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


	public void initialize(){
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

	public void loadExercises(){
		ExerciseFetch ef = new ExerciseFetch();
		ef.readExercises("1");
		exercises = ef.getExercises();

	}
	
}
