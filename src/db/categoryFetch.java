package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by William on 14.03.2016.
 */
public class categoryFetch{

    private ArrayList<String> categories = new ArrayList<String>();

    private static Connection connect = null;
    private static Statement statement = null;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/dag";
    static final String USER = "root";
    static final String PASS = "eple";

    public ArrayList<String> getCategories(){
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to a selected database...");
            connect = DriverManager.getConnection(DB_URL, USER, "eple");
            System.out.println("Connected database successfully...");
            PreparedStatement prstmnt = null;
            String get = "SELECT knavn from dag.kategori";
            prstmnt = connect.prepareStatement(get);
            ResultSet set = prstmnt.executeQuery();
            while (set.next()){
                categories.add(set.getString("knavn"));
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
        
        return categories;
    }
}