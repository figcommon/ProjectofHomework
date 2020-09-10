package dao;

import utils.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    protected Connection connection;
    protected Statement statement;
    protected ResultSet set;

    private Statement getStatement() {
        if (statement == null) {
            try {
                if (connection == null) {
                    connection = DBConnection.getConnection();
                }
                statement = connection.createStatement();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return statement;
    }

    protected int executeUpdate(String sql) throws SQLException {
        return getStatement().executeUpdate(sql);
    }
    protected ResultSet executeQuery(String sql) throws SQLException {
        return getStatement().executeQuery(sql);
    }
    protected void close() {
        if (set != null) {
            try {
                set.close();
                set = null;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
                statement = null;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}