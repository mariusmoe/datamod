package db;

import models.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Hallgeir on 14.03.2016.
 */
public class ExerciseFetch {

    public ExerciseFetch(){

    }

    private Collection<Exercise> exercises = new ArrayList<Exercise>();

    private static Connection connect = null;
    private static Statement statement = null;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dag";
    static final String USER = "root";
    static final String PASS = null;


    public Collection<Exercise> getExercises() {
        return exercises;
    }

    private void addExercise(Exercise ex){
        exercises.add(ex);
    }


    public void readExercises(String name){


        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            PreparedStatement prstmnt = null;
            String get = "SELECT * from dag.ovelse";
            prstmnt = connect.prepareStatement(get);
            ResultSet set = prstmnt.executeQuery();
            while (set.next()){
                String exerciseName = set.getString("navn");
                String description = set.getString("beskrivelse");
                String alternative = set.getString("ovelse_navn");
                String category = set.getString("kategori_knavn");
                addExercise(new Exercise(exerciseName, description, alternative, category));


            }
            set.close();






        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    connect.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


    }
}