package by.farshyniou.jdbc.repository.impl;

import by.farshyniou.jdbc.connection.JdbcConnection;
import by.farshyniou.jdbc.converter.ResultSetToEntityConverter;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.exceptions.RepositoryException;
import by.farshyniou.jdbc.repository.JdbcRepository;
import by.farshyniou.jdbc.utils.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_BREED;
import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_CAT;

public class CatJdbcRepository implements JdbcRepository<Long, Cat> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CatJdbcRepository.class.getName());
    private static final JdbcRepository<Long, Cat> INSTANCE = new CatJdbcRepository();

    public static JdbcRepository<Long, Cat> getInstance() {
        return INSTANCE;
    }

    private CatJdbcRepository() {
    }

    @Override
    public void createTable() {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_CAT);
        } catch (SQLException e) {
            LOGGER.error("Error during creating Cat table, {}", e.getMessage());
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
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(Queries.DELETE_FROM_CAT, Statement.RETURN_GENERATED_KEYS)) {
            LOGGER.info("Deleting Cat with id {} ...", id);
            pStatement.setLong(1, id);
            pStatement.executeUpdate();
            ResultSet keys = pStatement.getGeneratedKeys();
            if (keys.next()) {
                return keys.getLong(1);
            } else {
                LOGGER.info("Nothing to delete from cat for id {}", id);
            }

        } catch (SQLException exception) {
            LOGGER.error("Exception during deleting from Cat table, {}", exception.getMessage());
            throw new RepositoryException(exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Cat> findAll() {
        List<Cat> cats = new ArrayList<>();
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Queries.SELECT_FROM_CAT)) {

            while (resultSet.next()) {
                Cat cat = ResultSetToEntityConverter.convertToCat(resultSet);
                cats.add(cat);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return cats;
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
    public Optional<Cat> selectById(Long id) {
        return Optional.empty();
    }

    @Override
    public Boolean update(Cat cat) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(Queries.UPDATE_CAT)) {
            prStatement.setString(1, cat.getCatId());
            prStatement.setString(2, cat.getUrl());
            prStatement.setInt(3, cat.getBreedId());
            prStatement.setInt(4, cat.getId());
            return prStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            LOGGER.error("Error during updating Cat with id={}, {}", cat.getId(), exception.getMessage());
            throw new RepositoryException(exception.getMessage());
        }
    }
}
