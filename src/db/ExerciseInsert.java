package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controllers.WorkoutsPaneJava ;

public class ExerciseInsert{
    private static Connection connect = null;
    private static Statement statement = null;
    private WorkoutsPaneJava ex;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dag";
    static final String USER = "root";
    static final String PASS = "eple";

    public ExerciseInsert(WorkoutsPaneJava mainCtrl){
        this.ex = mainCtrl;
        setConnection();
    }

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

    public void uploadExercise() {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try{
            dbConnection = getConnection();
            String get = "INSERT INTO ovelse (navn, beskrivelse, ovelse_navn, kategori_knavn) VALUES (?, ?, ?, ?)";
            preparedStatement = dbConnection.prepareStatement(get);



            preparedStatement.setString(1, ex.getName());
            preparedStatement.setString(2, ex.getDescription());
            preparedStatement.setString(3, ex.getReplacement());
            preparedStatement.setString(4, ex.getCategory());

            preparedStatement.executeUpdate();

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
}