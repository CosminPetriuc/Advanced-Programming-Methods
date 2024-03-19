package repository;

import domain.Meal;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:identifier.sqlite";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Meal> getAll()
    {
        ArrayList<Meal> documents = new ArrayList<>();
        try
        {
            openConnection();
            // SQL query to select all rows from the "documents" table
            String selectString = "SELECT * FROM Meal;";
            try (PreparedStatement ps = conn.prepareStatement(selectString))
            {
                ResultSet resultSet = ps.executeQuery();

                // Iterate through the result set and create Document objects
                while (resultSet.next())
                {

                    String name = resultSet.getString("name");
                    int time = resultSet.getInt("time");
                    String ingredients = resultSet.getString("ingredients");

                    Meal meal = new Meal(name, time, ingredients);
                    documents.add(meal);
                }
            }
        }
        catch (SQLException e)
        {
            // Wrap SQLException in a RuntimeException and rethrow
            throw new RuntimeException(e);
        }
        finally
        {
            // Close the database connection in the finally block to ensure it always happens
            closeConnection();

        }
        return documents;
    }
}
