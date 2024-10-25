package by.farshyniou.jdbc.repository.impl;

import by.farshyniou.jdbc.connection.JdbcConnection;
import by.farshyniou.jdbc.converter.ResultSetToEntityConverter;
import by.farshyniou.jdbc.entity.breed.Breed;
import by.farshyniou.jdbc.entity.filter.breed.BreedFilter;
import by.farshyniou.jdbc.exceptions.RepositoryException;
import by.farshyniou.jdbc.repository.JdbcRepositoryWithBreedFilter;
import by.farshyniou.jdbc.utils.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_BREED;

public class BreedJdbcRepositoryImpl implements JdbcRepositoryWithBreedFilter<Long, Breed> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BreedJdbcRepositoryImpl.class);
    private static final JdbcRepositoryWithBreedFilter<Long, Breed> INSTANCE = new BreedJdbcRepositoryImpl();

    private BreedJdbcRepositoryImpl() {
    }

    public static JdbcRepositoryWithBreedFilter<Long, Breed> getInstance() {
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
    public Long delete(Long id) {
        return 0L;
    }

    @Override
    public List<Breed> findAll() {
        return List.of();
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
    public Optional<Breed> findById(Long id) {
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

    @Override
    public boolean insert(Breed entity) {
        return false;
    }

    @Override
    public List<Breed> findAllWithBreedFilter(BreedFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.getBreedId() != null) {
            parameters.add("%" + filter.getBreedId() + "%");
            whereSql.add("breed_id like ?");
        }
        if (filter.getBreedName() != null) {
            parameters.add("%" + filter.getBreedName() + "%");
            whereSql.add("breed_name like ?");
        }
        if (filter.getCountryCode() != null) {
            parameters.add("%" + filter.getCountryCode() + "%");
            whereSql.add("country_code like ?");
        }
        if (filter.getBreedTemperament() != null) {
            parameters.add("%" + filter.getBreedTemperament() + "%");
            whereSql.add("breed_temperament like ?");
        }
        parameters.add(filter.getLimit());
        parameters.add(filter.getOffset());
        String where = whereSql.stream().collect(Collectors.joining(
                " AND ",
                parameters.size() > 2 ? "WHERE " : "",
                " LIMIT ? OFFSET ?"
        ));
        String selectAll = Queries.SELECT_ALL_FROM_BREED + where;
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement prStatement = connection.prepareStatement(selectAll)) {
            for (int i = 0; i < parameters.size(); i++) {
                prStatement.setObject(i + 1, parameters.get(i));
            }
            ResultSet resultSet = prStatement.executeQuery();
            List<Breed> breeds = new ArrayList<>();
            while (resultSet.next()) {
                breeds.add(ResultSetToEntityConverter.convertToBreed(resultSet));
            }
            return breeds;
        } catch (SQLException exception) {
            LOGGER.error("Error during selecting all Breed entities with filter, {}", exception.getMessage());
            throw new RepositoryException(exception.getMessage());
        }
    }
}
