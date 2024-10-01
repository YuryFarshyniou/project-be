package by.farshyniou.jdbc.connection;

import by.farshyniou.utils.ConfigFileReader;
import by.farshyniou.utils.impl.ConfigFileReaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnection.class);

    private JdbcConnection() {
    }

    public static Connection getConnection() throws SQLException {
        ConfigFileReader configReader = new ConfigFileReaderImpl();
        LOGGER.info("Getting connection to db...");
        return DriverManager.getConnection(configReader.getJDBCUrl(),
                configReader.getJDBCName(),
                configReader.getJDBCPassword());
    }

}
