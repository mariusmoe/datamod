package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Hallgeir on 15.03.2016.
 */
public class SavedExercise {




    public IntegerProperty exSetsProperty() {
        return exSets;
    }

    public IntegerProperty exRepsProperty() {
        return exReps;
    }

    public IntegerProperty exWeightProperty() {
        return exWeight;
    }

    public IntegerProperty exLengthProperty() {
        return exLength;
    }

    public StringProperty exNameProperty() { return exName;}



    private StringProperty exName;
    private IntegerProperty exSets;
    private IntegerProperty exReps;
    private IntegerProperty exWeight;
    private IntegerProperty exLength;


    public SavedExercise(String name, int sets, int reps, int weight, int length){

        exName = new SimpleStringProperty(name);
        exSets = new SimpleIntegerProperty(sets);
        exReps = new SimpleIntegerProperty(reps);
        exWeight = new SimpleIntegerProperty(weight);
        exLength = new SimpleIntegerProperty(length);


    }

    public String getName(){
        return exName.getValue();
    }










}
