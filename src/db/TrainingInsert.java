package db;

/**
 * Created by Hallgeir on 16.03.2016.
 */

import controllers.MainController;
import javafx.collections.ObservableList;
import models.SavedExercise;
import models.Workout;

import java.sql.*;

public class TrainingInsert {
    private MainController mainCtrl;
    private Workout workout;
    private boolean isIndoorWorkout;
    public TrainingInsert(MainController mainCtrl, Workout workout, Boolean isIndoorWorkout) {
        this.mainCtrl = mainCtrl;
        this.workout = workout;
        this.isIndoorWorkout = isIndoorWorkout;
        this.autoIncValue = -1;
    }
    private static Connection connect = null;
    private static Statement statement = null;

    private int autoIncValue;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dag";
    static final String USER = "root";
    static final String PASS = null;

    public void setConnection(){

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to dag for insertion...");
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public Connection getConnection(){
        return connect;
    }

    public void upload() {

        setConnection();
        uploadWorkout();
        if (isIndoorWorkout){
            uploadIndoor();
        }
        else{
            uploadOutdoor();
        }

        uploadExerciseWorkoutRelation();

    }

    /*Uploads a row to 'trening' table. The following methods need the 'trening_id' that this insertion creates, which is stored in autoIncValue*/
    private void uploadWorkout() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try{
            dbConnection = getConnection();
            String get = "INSERT INTO trening (tidspunkt, varighet, personlig_form, prestasjon) VALUES (?, ?, ?, ?)";
            preparedStatement = dbConnection.prepareStatement(get, Statement.RETURN_GENERATED_KEYS);



            preparedStatement.setTimestamp(1, workout.getDateAsTimestamp());
            preparedStatement.setInt(2, workout.getDuration());
            preparedStatement.setInt(3, workout.getForm());
            preparedStatement.setInt(4, workout.getAchievement());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                autoIncValue = rs.getInt(1);
            }
            else{

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (preparedStatement != null) {
                    preparedStatement.close();
                }



            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    /*Uploads a row in 'innendors' table containing the referance to the 'trening' that was just inserted */
    private void uploadIndoor() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        /*if the 'trening_id' was successfully transferred from the above method..*/
        if (autoIncValue > 0){

            try{
                dbConnection = getConnection();
                String insert = "INSERT INTO innendors (luft, trening_idtrening, tilskuere) VALUES (?, ?, ?)";
                preparedStatement = dbConnection.prepareStatement(insert);

                preparedStatement.setInt(1, workout.getAirquality());
                preparedStatement.setInt(2, autoIncValue);
                preparedStatement.setInt(3, workout.getSpectatorsNum());

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else{
            try{
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (dbConnection != null) {
                    dbConnection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            }


    }

    /*Uploads a row into the 'utendors' table containing the referance to the 'trening' that was just inserted */
    private void uploadOutdoor() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        if (autoIncValue > 0){

            try{
                dbConnection = getConnection();
                String insert = "INSERT INTO utendors (temp, vartype, trening_idtrening) VALUES (?, ?, ?)";
                preparedStatement = dbConnection.prepareStatement(insert);

                preparedStatement.setInt(1,workout.getTemp() );
                preparedStatement.setString(2,workout.getWeather());
                preparedStatement.setInt(3, autoIncValue);

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            } finally{
                try{
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }


        } catch (SQLException e) {
                    e.printStackTrace();
                }}}
        else{

            try{
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (dbConnection != null) {
                    dbConnection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }
    private void uploadExerciseWorkoutRelation() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertExWork = "INSERT into ovelse_has_trening (ovelse_navn, trening_idtrening, belastning, sett, repetisjoner) VALUES (?, ?, ?, ?, ?)";
        try{
            dbConnection = getConnection();
            ObservableList<SavedExercise> savedExercises = mainCtrl.getExercises();
            for (SavedExercise svdEx : savedExercises){
                preparedStatement = dbConnection.prepareStatement(insertExWork);

                preparedStatement.setString(1,svdEx.getName());
                preparedStatement.setInt(2, autoIncValue);
                preparedStatement.setInt(3,  svdEx.getExWeight());
                preparedStatement.setInt(4, svdEx.getExSets());
                preparedStatement.setInt(5, svdEx.getExReps());

                preparedStatement.executeUpdate();
                preparedStatement.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

            try{

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        {

        }


    }

    }

