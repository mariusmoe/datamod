package controllers;

import java.util.ArrayList;

import db.ExerciseInsert;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import db.categoryFetch;

public class WorkoutsPaneJava{

	@FXML
	private TextField name;

	@FXML
	private ComboBox <String> category;

	@FXML
	private TextArea description;

	@FXML
	private TextField replacement;
	private ExerciseInsert exInsert;



	private ArrayList<String> categories;
	private categoryFetch categoryfetch;

	@FXML private void initialize(){
		categoryfetch = new categoryFetch();
		exInsert = new ExerciseInsert(this);

		setCategory();
	}


	public String getName(){
		return name.getText();
	}

	public String getDescription(){
		return description.getText();
	}

	public String getReplacement(){
		return replacement.getText();
	}

	public String getCategory(){
		return category.getValue();
	}

	public void setCategory(){
		categories = categoryfetch.getCategories();

		for(int i=0;i<categories.size();i++){
			category.getItems().add(categories.get(i));
		}
	}

	@FXML private void registerExercise(){

		exInsert.uploadExercise();
	}
}

