package by.farshyniou.jdbc.repository;

import by.farshyniou.jdbc.entity.breed.Breed;
import by.farshyniou.jdbc.entity.filter.breed.BreedFilter;

import java.util.List;

public interface JdbcRepositoryWithBreedFilter<K, E> extends JdbcRepository<K, E> {
    List<Breed> findAllWithBreedFilter(BreedFilter filter);
}
