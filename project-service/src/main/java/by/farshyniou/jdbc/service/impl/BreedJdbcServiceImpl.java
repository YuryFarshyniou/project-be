package by.farshyniou.jdbc.service.impl;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.converter.to_dto.ToDtoConverter;
import by.farshyniou.jdbc.converter.to_entity.ToEntityConverter;
import by.farshyniou.jdbc.entity.breed.Breed;
import by.farshyniou.jdbc.exception.EntityNotFoundException;
import by.farshyniou.jdbc.filter.BreedFilterDto;
import by.farshyniou.jdbc.repository.impl.BreedJdbcRepositoryImpl;
import by.farshyniou.jdbc.service.JdbcServiceWithBreedFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

public class BreedJdbcServiceImpl implements JdbcServiceWithBreedFilter<Long, BreedDto> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BreedJdbcServiceImpl.class);
    private static final JdbcServiceWithBreedFilter<Long, BreedDto> INSTANCE = new BreedJdbcServiceImpl();

    private BreedJdbcServiceImpl() {
    }

    public static JdbcServiceWithBreedFilter<Long, BreedDto> getInstance() {
        return INSTANCE;
    }

    @Override
    public void createTable() {
        BreedJdbcRepositoryImpl.getInstance().createTable();
    }

    @Override
    public Long delete(Long id) {
        return BreedJdbcRepositoryImpl.getInstance().delete(id);
    }

    @Override
    public List<BreedDto> findAll() {
        return BreedJdbcRepositoryImpl.getInstance().findAll()
                .stream().map(ToDtoConverter::convertToBreedDto)
                .toList();
    }

    @Override
    public DatabaseMetaData getMetaData() {
        return BreedJdbcRepositoryImpl.getInstance().getMetaData();
    }

    @Override
    public BreedDto findById(Long breedId) {
        return ToDtoConverter.convertToBreedDto(BreedJdbcRepositoryImpl.getInstance().findById(breedId).orElseThrow(
                () -> new EntityNotFoundException("Breed with id " + breedId + " not found")
        ));
    }

    @Override
    public boolean update(BreedDto breedDto) {
        return BreedJdbcRepositoryImpl.getInstance().update(ToEntityConverter.toBreedEntity(breedDto));
    }

    @Override
    public boolean insert(BreedDto dto) {
        return false;
    }

    @Override
    public List<BreedDto> findAllFromBreedWithFilter(BreedFilterDto breedFilterDto) {
        List<Breed> allBreedWithFilter = BreedJdbcRepositoryImpl.getInstance().findAllWithBreedFilter(ToEntityConverter.toBreedFilterEntity(breedFilterDto));
        if (!allBreedWithFilter.isEmpty()) {
            List<BreedDto> breeds = new ArrayList<>();
            allBreedWithFilter.forEach(breed -> breeds.add(ToDtoConverter.convertToBreedDto(breed)));
            return breeds;
        } else {
            throw new EntityNotFoundException("There aren't any Breed in the db for this criteria");
        }
    }
}
