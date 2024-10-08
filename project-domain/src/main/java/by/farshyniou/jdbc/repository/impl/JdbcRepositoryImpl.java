package by.farshyniou.jdbc.repository.impl;

import by.farshyniou.jdbc.connection.JdbcConnection;
import by.farshyniou.jdbc.repository.JdbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_BREED;
import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_CAT;

public class JdbcRepositoryImpl implements JdbcRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcRepositoryImpl.class);

    @Override
    public void createCatTable() throws SQLException {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_CAT);
        }
    }

    @Override
    public void createBreedTable() throws SQLException {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_BREED);
        }
    }

    @Override
    public void createTables() throws SQLException {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_BREED);
            statement.execute(CREATE_TABLE_CAT);
        }
    }


}
