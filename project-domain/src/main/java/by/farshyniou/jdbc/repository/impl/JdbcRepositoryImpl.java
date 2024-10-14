package by.farshyniou.jdbc.repository.impl;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.connection.JdbcConnection;
import by.farshyniou.jdbc.converter.ResultSetToEntityConverter;
import by.farshyniou.jdbc.converter.to_entity.ToEntityConverter;
import by.farshyniou.jdbc.entity.breed.Breed;
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
    public void insertIntoTablesFromApi() throws SQLException {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
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
            List<Breed> breeds = new ArrayList<>();
            catsDto.forEach(dto -> {
                BreedDto breedDto = dto.getBreeds().getFirst();
                if (breeds.stream().filter(breed -> breedDto.getBreedId().equals(breed.getBreedId()))
                        .findFirst().isEmpty()) {

                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_BREED);
                        preparedStatement.setString(1, breedDto.getBreedId());
                        preparedStatement.setString(2, breedDto.getName());
                        preparedStatement.setString(3, breedDto.getTemperament());
                        preparedStatement.setString(4, breedDto.getOrigin());
                        preparedStatement.setString(5, breedDto.getCountryCode());
                        preparedStatement.setString(6, breedDto.getDescription());
                        preparedStatement.setString(7, breedDto.getLifeSpan());
                        preparedStatement.setString(8, breedDto.getWikipediaUrl());
                        preparedStatement.executeUpdate();
                        ResultSet keys = preparedStatement.getGeneratedKeys();
                        if (keys.next()) {
                            int breedId = keys.getInt(1);
                            breedDto.setId(breedId);
                        }


                    } catch (SQLException e) {
                        LOGGER.error(e.getMessage());
                    }
                    breeds.add(ToEntityConverter.toBreedEntity(breedDto));
                }
            });
        }
    }


}
