package by.farshyniou.jdbc.repository;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.connection.JdbcConnection;
import by.farshyniou.jdbc.entity.filter.breed.BreedFilter;
import by.farshyniou.jdbc.exceptions.RepositoryException;
import by.farshyniou.jdbc.utils.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.List;
import java.util.Optional;

public interface JdbcRepository<K,E> {

    void createTable();

    void createTables();

    void insertIntoTablesExample();

    Long delete(K id);

    List<E> findAll();

    void dropTables();

    DatabaseMetaData getMetaData();

    Optional<E> selectById(K id);

    Boolean update(E entity);

    List<E> findAllWithFilter(BreedFilter breedFilter);


    default void insertIntoTablesFromApi(List<CatDto> cats) {

        try (Connection connection = JdbcConnection.getConnection()) {
            cats.forEach(dto -> {
                try {
                    BreedDto breedDto = dto.getBreeds().getFirst();
                    PreparedStatement selectFromBreedStatement = connection.prepareStatement(Queries.SELECT_BREED_ID_ID_FROM_BREED_WITH_BREED_ID);
                    selectFromBreedStatement.setFetchSize(5);
                    selectFromBreedStatement.setString(1, breedDto.getBreedId());
                    ResultSet selectResulSet = selectFromBreedStatement.executeQuery();
                    Integer breedId = null;
                    if (!selectResulSet.next()) {
                        PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_BREED, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, breedDto.getBreedId());
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
                            breedId = id.getInt(1);
                            breedDto.setId(breedId);
                        }
                        logger().info("Breed with id = {}  was added to db", breedDto.getBreedId());
                        preparedStatement.close();
                    } else {
                        breedId = selectResulSet.getInt("id");
                    }
                    PreparedStatement insertIntoCatStatement = connection.prepareStatement(Queries.INSERT_INTO_CAT);
                    insertIntoCatStatement.setString(1, dto.getCatId());
                    insertIntoCatStatement.setString(2, dto.getUrl());
                    insertIntoCatStatement.setInt(3, breedId);
                    insertIntoCatStatement.executeUpdate();
                    logger().info("Cat with id = {}  was added to db with breed = {}", dto.getCatId(), breedDto.getBreedId());
                    selectFromBreedStatement.close();
                    insertIntoCatStatement.close();
                } catch (SQLException e) {
                    logger().error("Error during inserting Cat or Breed entities from API, {}", e.getMessage());
                    throw new RepositoryException(e.getMessage());
                }
            });
        } catch (SQLException e) {
            logger().error("Error during inserting values from API, {}", e.getMessage());
            throw new RepositoryException(e.getMessage());
        }
    }

    private static Logger logger() {
        final class LogHolder {
            private static final Logger LOGGER = LoggerFactory.getLogger(JdbcRepository.class);
        }
        return LogHolder.LOGGER;
    }
}
