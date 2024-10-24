package by.farshyniou.jdbc.service;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.filter.BreedFilterDto;

import java.util.List;

public interface JdbcServiceWithBreedFilter<K, E> extends JdbcService<K, E> {

    List<BreedDto> findAllFromBreedWithFilter(BreedFilterDto breedFilterDto);
}
