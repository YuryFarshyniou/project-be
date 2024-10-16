package by.farshyniou.jdbc.repository;

import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.cat.Cat;

import by.farshyniou.jdbc.entity.breed.Breed;

import java.sql.DatabaseMetaData;
import java.util.List;
import java.util.Optional;

public interface JdbcRepository {

    void createCatTable() ;

    void createBreedTable() ;

    void createTables() ;

    void insertIntoTablesExample() ;

    Long deleteFromCatTable(Long catId) ;

    List<Cat> selectAllFromCatTable();

    void dropTables();

    void insertIntoTablesFromApi(List<CatDto> cats);

    DatabaseMetaData getMetaData();

    Optional<Breed> selectFromBreedById(Integer breedId);

    Boolean updateCat(Cat cat);


}
