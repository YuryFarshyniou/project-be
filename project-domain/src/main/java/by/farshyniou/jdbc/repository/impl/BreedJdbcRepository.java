package by.farshyniou.jdbc.repository.impl;
import by.farshyniou.jdbc.connection.JdbcConnection;
import by.farshyniou.jdbc.converter.ResultSetToEntityConverter;
import by.farshyniou.jdbc.entity.breed.Breed;

import by.farshyniou.jdbc.exceptions.RepositoryException;
import by.farshyniou.jdbc.repository.JdbcRepository;
import by.farshyniou.jdbc.utils.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_BREED;
import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_CAT;

public class BreedJdbcRepository implements JdbcRepository<Long,Breed> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BreedJdbcRepository.class);
    private static final JdbcRepository<Long,Breed> INSTANCE = new BreedJdbcRepository();
    private BreedJdbcRepository() {}
    public static JdbcRepository<Long,Breed> getInstance() {
        return INSTANCE;
    }


    @Override
    public void createTable() {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_BREED);
        } catch (SQLException e) {
            LOGGER.error("Error during creating Breed table, {}", e.getMessage());
        }
    }

    @Override
    public void createTables() {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_BREED);
            statement.execute(CREATE_TABLE_CAT);
        } catch (SQLException e) {
            LOGGER.error("Error during creating tables, {}", e.getMessage());
        }
    }
    @Override
    public void insertIntoTablesExample() {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(Queries.INSERT_INTO_BREED_EXAMPLE);
            statement.executeUpdate(Queries.INSERT_INTO_CAT_EXAMPLE);
        } catch (SQLException e) {
            LOGGER.error("Error during inserting into tables example values, {}", e.getMessage());
        }
    }

    @Override
    public Long delete(Long id) {
        return 0L;
    }

    @Override
    public List<Breed> findAll() {
        return List.of();
    }

    @Override
    public void dropTables() {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(Queries.DROP_TABLES);
        } catch (SQLException exception) {
            LOGGER.error("Error during dropping tables, {}", exception.getMessage());
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    public DatabaseMetaData getMetaData() {
        DatabaseMetaData metaData;
        try (Connection connection = JdbcConnection.getConnection()) {
            metaData = connection.getMetaData();
        } catch (SQLException e) {
            LOGGER.error("Error during getting metaData ,{}", e.getMessage());
            throw new RepositoryException(e.getMessage());
        }
        return metaData;
    }

    @Override
    public Optional<Breed> selectById(Long id) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(Queries.SELECT_FROM_BREED_WITH_BREED_ID)) {
            prepStatement.setLong(1, id);
            ResultSet resultSet = prepStatement.executeQuery();
            Breed breed = null;
            if (resultSet.next()) {
                breed = ResultSetToEntityConverter.convertToBreed(resultSet);
            }
            return Optional.ofNullable(breed);
        } catch (SQLException exception) {
            LOGGER.error("Error during selecting Breed by ID, {}", exception.getMessage());
            throw new RepositoryException(exception.getMessage());
        }
    }

    @Override
    public Boolean update(Breed entity) {
        return null;
    }
}
