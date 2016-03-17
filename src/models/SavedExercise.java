package models;

import javafx.beans.property.*;

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

    public StringProperty exNameProperty() { return exName;}

    public boolean isStrength(){ return isStrength;}



    private StringProperty exName;
    private IntegerProperty exSets;

    public int getExSets() {
        return exSets.get();
    }

    public int getExReps() {
        return exReps.get();
    }
    /*Because of a flaw in our database design that was discovered too late in the project, we use this workaround to assign the length of a cardioworkout as the 'weight' of the workout*/
    public int getExWeight() {
        if (isStrength())
            return exWeight.getValue();
        return exLength.getValue();
    }


    private IntegerProperty exReps;
    private IntegerProperty exWeight;
    private boolean isStrength;
    private IntegerProperty exLength;


    public SavedExercise(String name, int sets, int reps, int weight, boolean isStrength){

        exName = new SimpleStringProperty(name);
        exSets = new SimpleIntegerProperty(sets);
        exReps = new SimpleIntegerProperty(reps);
        if (isStrength){
            exWeight = new SimpleIntegerProperty(weight);;
            exLength = new SimpleIntegerProperty(0);;
        }
        else {
            exWeight = new SimpleIntegerProperty(0);
            exLength = new SimpleIntegerProperty(weight);
        }

        this.isStrength = isStrength;


    }

    public String getName(){
        return exName.getValue();
    }


    public IntegerProperty exLengthProperty() {
        return exLength;
    }
}
