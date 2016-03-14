package gui;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;

public class Exercise {
	private Property<String> nameProperty = new SimpleStringProperty();
	private Property<String> descriptionProperty = new SimpleStringProperty();

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


}
