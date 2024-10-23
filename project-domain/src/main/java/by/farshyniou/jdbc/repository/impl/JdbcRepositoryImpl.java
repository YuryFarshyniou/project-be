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
    public List<Breed> findAllWithFilter(BreedFilter breedFilter) {
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
