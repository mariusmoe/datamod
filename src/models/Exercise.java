package models;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;

public class Exercise {

	public Exercise(){

	}


	public Exercise(String name, String description, String alternative, String category){
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
		this.alternative = new SimpleStringProperty(alternative);
		this.category = new SimpleStringProperty(category);

	}


	private Property<String> name;
	private Property<String> description;
	private Property<String> alternative;
	private Property<String> category;


	void setDescription(String descr){
		description.setValue(descr);
	}
	public String getDescription(){
		return description.getValue();
	}
	void setName(String name){
		this.name.setValue(name);
	}
	public String getName(){
		return name.getValue();
	}
	void setAlternative(String alternative){
		this.alternative.setValue(alternative);
	}
	public String getAlternative(){
		return alternative.getValue();
	}
	void setCategory(String category) {
		this.category.setValue(category);
	}
	public String getCategory(){
		return category.getValue();
	}


	public Property<String> nameProperty() {
		return name;
	}

	public Property<String> descriptionProperty() {
		return description;
	}

	public Property<String> alternativeProperty() {
		return alternative;
	}

	public Property<String> categoryProperty() {
		return category;
	}


}
