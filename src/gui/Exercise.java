package gui;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Exercise {
	private Property<String> nameProperty = new SimpleStringProperty();
	private Property<Number> setProperty = new SimpleIntegerProperty();
	private Property<Number> repProperty = new SimpleIntegerProperty();
	private Property<Number> weightProperty = new SimpleIntegerProperty();
	
	void setName(String name){
		nameProperty.setValue(name);
	}
	
	String getName(){
		return nameProperty.getValue();
	}
	
	void setSet(int set){
		setProperty.setValue(set);
	}
	
	int getSet(){
		return setProperty.getValue().intValue();
	}
	
	void setRep(int rep){
		repProperty.setValue(rep);
	}
	int getRep(){
		return repProperty.getValue().intValue();
	}
	
	void setWeight(int set){
		weightProperty.setValue(set);
	}
	int getWeight(){
		return weightProperty.getValue().intValue();
	}
	
	public Property<Number> getSetProperty(){
		return setProperty;
	}

	public Property<Number> getRepProperty() {
		return repProperty;
	}


	public Property<Number> getWeightProperty() {
		return weightProperty;
	}

	

}
