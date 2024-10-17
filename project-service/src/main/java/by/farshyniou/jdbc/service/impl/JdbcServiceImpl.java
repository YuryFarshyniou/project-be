package by.farshyniou.jdbc.service.impl;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.converter.to_dto.ToDtoConverter;
import by.farshyniou.jdbc.converter.to_entity.ToEntityConverter;
import by.farshyniou.jdbc.entity.breed.Breed;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.exception.EntityNotFoundException;
import by.farshyniou.jdbc.filter.BreedFilterDto;
import by.farshyniou.jdbc.repository.impl.JdbcRepositoryImpl;
import by.farshyniou.jdbc.service.JdbcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class JdbcServiceImpl implements JdbcService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcServiceImpl.class);
    private static final JdbcService INSTANCE = new JdbcServiceImpl();

    public static JdbcService getInstance() {
        return INSTANCE;
    }

    @Override
    public void createCatTable() {
        JdbcRepositoryImpl.getInstance().createCatTable();

    }

    @Override
    public void createBreedTable() {
        JdbcRepositoryImpl.getInstance().createBreedTable();
    }

    @Override
    public void createTables() {
        JdbcRepositoryImpl.getInstance().createTables();
    }

    @Override
    public void insertIntoTables() {
        JdbcRepositoryImpl.getInstance().insertIntoTablesExample();
    }

    @Override
    public Long deleteFromCatTable(Long catId) {
        return JdbcRepositoryImpl.getInstance().deleteFromCatTable(catId);
    }

    @Override
    public void dropTables() {
        JdbcRepositoryImpl.getInstance().dropTables();
    }

    @Override
    public List<Cat> selectAllFromCatTable() {
        return JdbcRepositoryImpl.getInstance().selectAllFromCatTable();
    }

    @Override
    public void insertIntoTablesFromApi(List<CatDto> cats) {
        JdbcRepositoryImpl.getInstance().insertIntoTablesFromApi(cats);

    }

    @Override
    public DatabaseMetaData getMetaData() {
        return JdbcRepositoryImpl.getInstance().getMetaData();
    }

    @Override
    public BreedDto selectBreedById(Integer breedId) {
        Optional<Breed> breed = JdbcRepositoryImpl.getInstance().selectFromBreedById(breedId);
        if (breed.isPresent()) {
            return ToDtoConverter.convertToBreedDto(breed.get());
        } else {
            throw new EntityNotFoundException("Breed entity hasn't been found with id: " + breedId);
        }
    }

    @Override
    public boolean updateCat(CatDto catDto) {
        return JdbcRepositoryImpl.getInstance().updateCat(ToEntityConverter.toCatEntity(catDto));
    }

    @Override
    public List<BreedDto> findAllFromBreedWithFilter(BreedFilterDto breedFilterDto) {
        Optional<List<Breed>> allBreedWithFilter = JdbcRepositoryImpl.getInstance().findAllBreedWithFilter(ToEntityConverter.toBreedFilterEntity(breedFilterDto));
        if (allBreedWithFilter.get().size() > 0) {
            List<BreedDto> breeds = new ArrayList<>();
            allBreedWithFilter.get().forEach(breed -> breeds.add(ToDtoConverter.convertToBreedDto(breed)));
            return breeds;
        } else {
            throw new EntityNotFoundException("There aren't any Breed in the db for this criteria");
        }
    }
}
