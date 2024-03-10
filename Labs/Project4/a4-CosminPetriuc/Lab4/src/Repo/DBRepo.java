package Repo;

import Domain.Identifiable;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.Objects;
public abstract class DBRepo<T extends Identifiable<U>, U> extends MemoryRepo<T,U> {
    protected static final String JDBC_URL = "jdbc:sqlite:A4.sqlite";

    protected Connection conn = null;

    abstract protected void createSchema();

    abstract protected T buildEntity(ResultSet resultSet) throws SQLException;



   public void openConnection() {
        try {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}