import java.sql.*;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.Scanner;

public class Cubes_Database {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/cubes";
    static final String USER = "cj";
    static final String PASSWORD = "password";

    private String TName;
    private String BName;
    private String timeC;



    protected Connection conn;
    protected Statement state;

    protected String prepInsert;
    protected  static PreparedStatement psInsert;
    protected String prepUpdate;
    protected PreparedStatement psUpdate;
    protected String prepDelete;
    protected PreparedStatement psDelete;

    protected ResultSet resultSet;
    protected String selectAll;

    protected LinkedList<Bot> allBots;

    Cubes_Database() throws SQLException{
        TName = "cubes";
        BName =  "cube_solver";
        timeC = "how_long";
        allBots = new LinkedList<Bot>();


//        psUpdate = conn.prepareStatement(prepUpdate);
//        psDelete = conn.prepareStatement(prepDelete);
//        psInsert = conn.prepareStatement(prepInsert);

    }

    public void connectDB() throws SQLException{
        //Cubes_Database cubes = new Cubes_Database();

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class;" +
                    " check you have drivers and classpath configured correctly");
            cnfe.printStackTrace();
            System.exit(-1);
        }

        try {
            Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement();

            //String refresh = "Drop table cubes";
            // statement.executeUpdate(refresh);

            String createTableSQL = "CREATE TABLE IF NOT EXISTS cubes (cube_solver varchar(50), how_long double)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Created cubes table");

            String addDataSQL = "INSERT INTO cubes VALUES ('Cubestormer II robot', 5.270)";
            statement.executeUpdate(addDataSQL);

            addDataSQL = "INSERT INTO cubes VALUES ('Fakhri Raihaan (using his feet)', 27.93)";
            statement.executeUpdate(addDataSQL);

            addDataSQL = "INSERT INTO cubes VALUES ('Ruxin Liu (age 3)', 99.33)";
            statement.executeUpdate(addDataSQL);

            addDataSQL = "INSERT INTO cubes VALUES ('Mats Valk (human record holder)', 6.27)";
            statement.executeUpdate(addDataSQL);




             prepUpdate = "Update Cubes set how_long = ? where cube_solver = ?";
             psUpdate = conn.prepareStatement(prepUpdate);
             prepDelete = "Delete from Cubes where cube_solver like ?";
             psDelete = conn.prepareStatement(prepDelete);

//            while (rs.next()) {
//                String cubeSolver = rs.getString("cube_solver");
//                double howLong = rs.getDouble("how_long");
//                System.out.println("Cube solver name = " + cubeSolver + "Time to complete = " + howLong);
//

            String blah = "grant select, insert, update, delete, create, drop on vet.* to 'cj'@'localhost'";

            //rs.close();
//            statement.close();
//            conn.close();

        } catch (SQLException se){
            se.printStackTrace();
        }

        System.out.println("Goodbye");
    }

    public ResultSet queryDB() {
        try {
            Connection connect = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = connect.createStatement();

            String fetchAllDataSQL = "SELECT * FROM cubes";
            ResultSet rs = statement.executeQuery(fetchAllDataSQL);
            conn = connect;
            state = statement;

            return rs;
        } catch (SQLException error) {
            System.out.println("Error");
            return null;
        }
    }

    public void addToDB(String newBot, Double newTime) {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()){

            prepInsert = "Insert into Cubes values (? , ?)";
            psInsert = conn.prepareStatement(prepInsert);

                psInsert.setString(1, newBot);
                psInsert.setDouble(2, newTime);

                psInsert.executeUpdate();

            } catch(SQLException error) {
                error.printStackTrace();
            }
        }

    public void updateToDB(String newBot, Double newTime) {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()){

            prepUpdate = "Insert into Cubes values (? , ?)";
            psUpdate = conn.prepareStatement(prepUpdate);

            psUpdate.setString(2, newBot);
            psUpdate.setDouble(1, newTime);

            psUpdate.executeUpdate();

        } catch(SQLException error) {
            error.printStackTrace();
        }
    }

    public void DeleteToDB(String newBot) {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
             Statement statement = conn.createStatement()){

            prepDelete = "Insert into Cubes values (? , ?)";
            psDelete = conn.prepareStatement(prepDelete);

            psDelete.setString(1, newBot);

            psDelete.executeUpdate();

        } catch(SQLException error) {
            error.printStackTrace();
        }
    }
}

