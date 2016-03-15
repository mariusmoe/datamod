package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Hallgeir on 15.03.2016.
 */
public class SavedExercise extends Exercise {

    IntegerProperty exSets;
    IntegerProperty exReps;
    IntegerProperty exWeight;


    public SavedExercise(int sets, int reps, int weight, String name, String description, String alternative, String category){
        super(name, description, alternative, category);
        exSets = new SimpleIntegerProperty(sets);
        exReps = new SimpleIntegerProperty(reps);
        exWeight = new SimpleIntegerProperty(weight);

    }










}
