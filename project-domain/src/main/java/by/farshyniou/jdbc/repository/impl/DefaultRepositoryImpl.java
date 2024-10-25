package by.farshyniou.jdbc.repository.impl;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat_api.CatApi;
import by.farshyniou.jdbc.connection.JdbcConnection;
import by.farshyniou.jdbc.exceptions.RepositoryException;
import by.farshyniou.jdbc.repository.DefaultRepository;
import by.farshyniou.jdbc.utils.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_BREED;
import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_CAT;

public class DefaultRepositoryImpl implements DefaultRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRepositoryImpl.class);
    private static final DefaultRepository INSTANCE = new DefaultRepositoryImpl();

    private DefaultRepositoryImpl() {
    }

    public static DefaultRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertIntoTablesFromApi(List<CatApi> cats) {
        try (Connection connection = JdbcConnection.getConnection()) {
            PreparedStatement insertIntoCatStatement = connection.prepareStatement(Queries.INSERT_INTO_CAT);
            cats.forEach(catApi -> {
                try {
                    BreedDto breedDto = catApi.getBreeds().getFirst();
                    PreparedStatement selectFromBreedStatement = connection.prepareStatement(Queries.SELECT_ID_FROM_BREED_WITH_BREED_ID);
                    selectFromBreedStatement.setFetchSize(5);
                    selectFromBreedStatement.setString(1, breedDto.getBreedShort());
                    ResultSet selectResulSet = selectFromBreedStatement.executeQuery();
                    Long breedId = null;
                    if (!selectResulSet.next()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_BREED, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, breedDto.getBreedShort());
                        preparedStatement.setString(2, breedDto.getName());
                        preparedStatement.setString(3, breedDto.getTemperament());
                        preparedStatement.setString(4, breedDto.getOrigin());
                        preparedStatement.setString(5, breedDto.getCountryCode());
                        preparedStatement.setString(6, breedDto.getDescription());
                        preparedStatement.setString(7, breedDto.getLifeSpan());
                        preparedStatement.setString(8, breedDto.getWikipediaUrl());
                        preparedStatement.execute();
                        ResultSet id = preparedStatement.getGeneratedKeys();
                        if (id.next()) {
                            breedId = id.getLong(1);
                            breedDto.setId(breedId);
                        }
                        LOGGER.info("Breed with id = {}  was added to db", breedDto.getBreedShort());
                        preparedStatement.close();
                    } else {
                        breedId = selectResulSet.getLong("id");
                    }
                    insertIntoCatStatement.setString(1, catApi.getCatId());
                    insertIntoCatStatement.setString(2, catApi.getUrl());
                    insertIntoCatStatement.setLong(3, breedId);
                    insertIntoCatStatement.addBatch();
                    LOGGER.info("Cat with id = {}  was added to db with breed = {}", catApi.getCatId(), breedDto.getBreedShort());
                    selectFromBreedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error("Error during inserting Cat or Breed entities from API, {}", e.getMessage());
                    throw new RepositoryException(e.getMessage());
                }
            });
            insertIntoCatStatement.executeBatch();
            insertIntoCatStatement.close();
        } catch (SQLException e) {
            LOGGER.error("Error during inserting values from API, {}", e.getMessage());
            throw new RepositoryException(e.getMessage());
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
    public void dropTables() {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(Queries.DROP_TABLES);
        } catch (SQLException exception) {
            LOGGER.error("Error during dropping tables, {}", exception.getMessage());
            throw new RepositoryException(exception.getMessage());
        }
    }
}
