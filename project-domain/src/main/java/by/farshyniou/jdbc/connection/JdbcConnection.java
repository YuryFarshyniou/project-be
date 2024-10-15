package by.farshyniou.jdbc.connection;

import by.farshyniou.jdbc.exceptions.ConnectionException;
import by.farshyniou.jdbc.utils.ConfigFileReader;
import by.farshyniou.jdbc.utils.impl.ConfigFileReaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class JdbcConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnection.class);
    private static BlockingQueue<Connection> pool;

    static {
        initConnectionPool();
    }

    private static void initConnectionPool() {
        ConfigFileReader configReader = new ConfigFileReaderImpl();
        int jdbcPoolSize = configReader.getJDBCPoolSize();
        pool = new ArrayBlockingQueue<>(configReader.getJDBCPoolSize());
        for (int i = 0; i < jdbcPoolSize; i++) {
            Connection connection = openConnection();
            Connection proxyConnection =(Connection) Proxy.newProxyInstance(JdbcConnection.class.getClassLoader(),
                    new Class[]{Connection.class},
                    ((proxy, method, args) -> method.getName().equals("close") ? pool.add((Connection) proxy)
                            : method.invoke(connection, args)));

            pool.add(proxyConnection);
        }
        LOGGER.info("Connection pool has been initialized with size: {} ", pool.size());
    }

    public static Connection getConnection() {
        try {
            LOGGER.debug("Connection has been taken from pool..");
            return pool.take();
        } catch (InterruptedException e) {
            LOGGER.error("Problems with getting exception from connection pool, {}", e.getMessage());
        }
        return null;
    }


    private JdbcConnection() {
    }

    private static Connection openConnection() {
        ConfigFileReader configReader = new ConfigFileReaderImpl();
        try {
            return DriverManager.getConnection(configReader.getJDBCUrl(),
                    configReader.getJDBCName(),
                    configReader.getJDBCPassword());
        } catch (SQLException e) {
            LOGGER.error("Problems with open connection,  {}", e.getMessage());
            throw new ConnectionException("Problems with open connection");
        }

    }
}
