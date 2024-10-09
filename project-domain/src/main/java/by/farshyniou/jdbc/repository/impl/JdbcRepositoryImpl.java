package by.farshyniou.jdbc.repository.impl;

import by.farshyniou.jdbc.connection.JdbcConnection;
import by.farshyniou.jdbc.repository.JdbcRepository;
import by.farshyniou.jdbc.utils.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
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

    @Override
    public void insertIntoTables() throws SQLException {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(Queries.INSERT_INTO_BREED);
            statement.executeUpdate(Queries.INSERT_INTO_CAT);

        }
    }

    @Override
    public void insertIntoCatTable() throws SQLException {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(Queries.INSERT_INTO_CAT);
        }
    }

    @Override
    public void deleteFromCatTable() throws SQLException {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(Queries.DELETE_FROM_CAT.formatted(1));
        }
    }

    @Override
    public void selectAllFromCatTable(){
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Queries.SELECT_FROM_CAT)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LOGGER.info(resultSet.getString("cat_url"));
                LOGGER.info(resultSet.getString("breed_name"));
            }
        }
        catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void dropTables() throws SQLException {
        try (Connection connection = JdbcConnection.getConnection() ;
        Statement statement = connection.createStatement()) {
            statement.execute(Queries.DROP_TABLES);
        }
    }


}
