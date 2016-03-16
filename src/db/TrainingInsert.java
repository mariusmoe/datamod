package db;

/**
 * Created by Hallgeir on 16.03.2016.
 */

import models.Workout;

import java.sql.*;

public class TrainingInsert {
    private Workout workout;
    private boolean isIndoorWorkout;
    public TrainingInsert(Workout workout, Boolean isIndoorWorkout) {
        this.workout = workout;
        this.isIndoorWorkout = isIndoorWorkout;
    }
    private static Connection connect = null;
    private static Statement statement = null;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dag";
    static final String USER = "root";
    static final String PASS = null;

    public void upload() {


        uploadWorkout();
        if (isIndoorWorkout){
            uploadIndoor();
        }
        else{
            uploadOutdoor();
        }

        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to dag for insertion...");
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            PreparedStatement prstmnt = null;
            String get = "INSERT INTO trening (idtrening, tidspunkt, varighet, personlig_form, prestasjon, maal_maal_id) ";
            prstmnt = connect.prepareStatement(get);
            ResultSet set = prstmnt.executeQuery();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void uploadWorkout() {
    }

    private void uploadIndoor() {
    }

    private void uploadOutdoor() {
    }
}
