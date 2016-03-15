package gui;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;

public class Exercise {

	public Exercise(String name, String description, String alternative, String category){
		setName(name);
		setDescription(description);
		setAlternative(alternative);
		setCategory(category);
	}


	private Property<String> nameProperty = new SimpleStringProperty();
	private Property<String> descriptionProperty = new SimpleStringProperty();
	private Property<String> alternativeProperty = new SimpleStringProperty();
	private Property<String> categoryProperty = new SimpleStringProperty();


	void setDescription(String descr){
		descriptionProperty.setValue(descr);
	}
	String getDescription(){
		return descriptionProperty.getValue();
	}
	void setName(String name){
		nameProperty.setValue(name);
	}
	String getName(){
		return nameProperty.getValue();
	}
	void setAlternative(String alternative){
		alternativeProperty.setValue(alternative);
	}
	String getAlternative(){
		return alternativeProperty.getValue();
	}
	void setCategory(String category) {
		categoryProperty.setValue(category);
	}
	String getCategory(){
		return categoryProperty.getValue();
	}



}
