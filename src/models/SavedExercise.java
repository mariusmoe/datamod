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

    public BooleanProperty getExStrengthTrue(){ return exStrengthTrue;}



    private StringProperty exName;
    private IntegerProperty exSets;

    public int getExSets() {
        return exSets.get();
    }

    public int getExReps() {
        return exReps.get();
    }

    public int getExWeight() {
        return exWeight.get();
    }


    private IntegerProperty exReps;
    private IntegerProperty exWeight;
    private BooleanProperty exStrengthTrue;


    public SavedExercise(String name, int sets, int reps, int weight, boolean strengthTrue){

        exName = new SimpleStringProperty(name);
        exSets = new SimpleIntegerProperty(sets);
        exReps = new SimpleIntegerProperty(reps);
        exWeight = new SimpleIntegerProperty(weight);
        exStrengthTrue = new SimpleBooleanProperty(strengthTrue);


    }

    public String getName(){
        return exName.getValue();
    }










}
