package by.farshyniou.jdbc.repository.impl;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.connection.JdbcConnection;
import by.farshyniou.jdbc.converter.ResultSetToEntityConverter;
import by.farshyniou.jdbc.entity.breed.Breed;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.entity.filter.breed.BreedFilter;
import by.farshyniou.jdbc.exceptions.RepositoryException;
import by.farshyniou.jdbc.repository.JdbcRepository;
import by.farshyniou.jdbc.utils.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_BREED;
import static by.farshyniou.jdbc.utils.Queries.CREATE_TABLE_CAT;

public class JdbcRepositoryImpl implements JdbcRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcRepositoryImpl.class);

    private static final JdbcRepository INSTANCE = new JdbcRepositoryImpl();

    private JdbcRepositoryImpl() {
    }

    public static JdbcRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void createCatTable() {
        try (Connection connection = JdbcConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_CAT);
        } catch (SQLException e) {
            LOGGER.error("Error during creating Cat table, {}", e.getMessage());
        }
    }

    @Override
    public void createBreedTable() {
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
    public Long deleteFromCatTable(Long catId) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(Queries.DELETE_FROM_CAT, Statement.RETURN_GENERATED_KEYS)) {
            LOGGER.info("Deleting Cat with id {} ...", catId);
            pStatement.setLong(1, catId);
            pStatement.executeUpdate();
            ResultSet keys = pStatement.getGeneratedKeys();
            if (keys.next()) {
                return keys.getLong(1);
            } else {
                LOGGER.info("Nothing to delete from cat for id {}", catId);
            }

        } catch (SQLException exception) {
            LOGGER.error("Exception during deleting from Cat table, {}", exception.getMessage());
            throw new RepositoryException(exception.getMessage());
        }
        return null;
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
    public void insertIntoTablesFromApi(List<CatDto> catsDto) {

        try (Connection connection = JdbcConnection.getConnection()) {
            catsDto.forEach(dto -> {
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
                    LOGGER.error("Error during inserting Cat or Breed entities from API, {}", e.getMessage());
                    throw new RepositoryException(e.getMessage());
                }
            });
        } catch (SQLException e) {
            LOGGER.error("Error during inserting values from API, {}", e.getMessage());
            throw new RepositoryException(e.getMessage());
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
    public Optional<Breed> selectFromBreedById(Integer breedId) {
        try (Connection connection = JdbcConnection.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(Queries.SELECT_FROM_BREED_WITH_BREED_ID)) {
            prepStatement.setInt(1, breedId);
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
    public Boolean updateCat(Cat cat) {
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

    @Override
    public Optional<List<Breed>> findAllBreedWithFilter(BreedFilter breedFilter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (breedFilter.getBreedId() != null) {
            parameters.add("%" + breedFilter.getBreedId() + "%");
            whereSql.add("breed_id like ?");
        }
        if (breedFilter.getBreed_name() != null) {
            parameters.add("%" + breedFilter.getBreed_name() + "%");
            whereSql.add("breed_name like ?");
        }
        if (breedFilter.getCountryCode() != null) {
            parameters.add("%" + breedFilter.getCountryCode() + "%");
            whereSql.add("country_code like ?");
        }
        if (breedFilter.getBreed_temperament() != null) {
            parameters.add("%" + breedFilter.getBreed_temperament() + "%");
            whereSql.add("breed_temperament like ?");
        }
        parameters.add(breedFilter.getLimit());
        parameters.add(breedFilter.getOffset());
        String where = whereSql.stream().collect(Collectors.joining(
                " AND ",
                "WHERE ",
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
            return Optional.of(breeds);
        } catch (SQLException exception) {
            LOGGER.error("Error during selecting all Breed entities with filter, {}", exception.getMessage());
            throw new RepositoryException(exception.getMessage());
        }
    }


}
