package by.farshyniou.jdbc.connector;

import by.farshyniou.jdbc.connection.JdbcConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcConnector {

    public Connection getJdbcConnection() throws SQLException {
        return JdbcConnection.getConnection();
    }
}
