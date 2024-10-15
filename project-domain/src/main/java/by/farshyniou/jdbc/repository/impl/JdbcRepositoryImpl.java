package by.farshyniou.jdbc.repository.impl;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.connection.JdbcConnection;
import by.farshyniou.jdbc.converter.ResultSetToEntityConverter;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.repository.JdbcRepository;
import by.farshyniou.jdbc.utils.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public void insertIntoTablesExample() throws SQLException {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(Queries.INSERT_INTO_BREED_EXAMPLE);
            statement.executeUpdate(Queries.INSERT_INTO_CAT_EXAMPLE);

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
    public List<Cat> selectAllFromCatTable() {
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
    public void dropTables() throws SQLException {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(Queries.DROP_TABLES);
        }
    }

    @Override
    public void insertIntoTablesFromApi(List<CatDto> catsDto) throws SQLException {

        try (Connection connection = JdbcConnection.getConnection()) {
            catsDto.forEach(dto -> {
                try {
                    BreedDto breedDto = dto.getBreeds().getFirst();
                    PreparedStatement selectFromBreedStatement = connection.prepareStatement(Queries.SELECT_FROM_BREED_WITH_BREED_ID);
                    selectFromBreedStatement.setFetchSize(5);
                    selectFromBreedStatement.setString(1, breedDto.getBreedId());
                    ResultSet selectResulSet = selectFromBreedStatement.executeQuery();
                    Integer breedId = null;
                    if (!selectResulSet.next()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_BREED);
                        preparedStatement.setString(1, breedDto.getBreedId());
                        preparedStatement.setString(2, breedDto.getName());
                        preparedStatement.setString(3, breedDto.getTemperament());
                        preparedStatement.setString(4, breedDto.getOrigin());
                        preparedStatement.setString(5, breedDto.getCountryCode());
                        preparedStatement.setString(6, breedDto.getDescription());
                        preparedStatement.setString(7, breedDto.getLifeSpan());
                        preparedStatement.setString(8, breedDto.getWikipediaUrl());
                        preparedStatement.execute();
                        ResultSet id = preparedStatement.getResultSet();
                        if (id.next()) {
                            breedId = id.getInt(1);
                            breedDto.setId(breedId);
                        }
                        LOGGER.info("Breed with id = {}  was added to db", breedDto.getBreedId());
                        preparedStatement.close();
                    } else {
                        breedId = selectResulSet.getInt("id");
                    }
                    PreparedStatement insertIntoCatStatement = connection.prepareStatement(Queries.INSERT_INTO_CAT);
                    insertIntoCatStatement.setString(1, dto.getCatId());
                    insertIntoCatStatement.setString(2, dto.getUrl());
                    insertIntoCatStatement.setInt(3, breedId);
                    insertIntoCatStatement.executeUpdate();
                    LOGGER.info("Cat with id = {}  was added to db with breed = {}", dto.getCatId(), breedDto.getBreedId());
                    selectFromBreedStatement.close();
                    insertIntoCatStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }
            });
        }
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        DatabaseMetaData metaData;
        try (Connection connection = JdbcConnection.getConnection()) {
            metaData = connection.getMetaData();
        }
        return metaData;
    }


}
