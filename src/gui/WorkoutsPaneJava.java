package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class WorkoutsPaneJava{
	
	@FXML
	private TextField name;
	
	@FXML
	private ComboBox <String> category;
	
	@FXML
	private TextArea description;
	
	@FXML
	private TextField replacement;
	
	@FXML
	private Button register;
	
	
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
}