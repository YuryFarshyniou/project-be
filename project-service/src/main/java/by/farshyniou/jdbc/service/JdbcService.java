package by.farshyniou.jdbc.service;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.filter.BreedFilterDto;

import java.sql.DatabaseMetaData;
import java.util.List;

public interface JdbcService {

    void createCatTable();

    void createBreedTable();

    void createTables();

    void insertIntoTables();

    Long deleteFromCatTable(Long catId);

    void dropTables();

    List<Cat> selectAllFromCatTable();

    void insertIntoTablesFromApi(List<CatDto> cats);

    DatabaseMetaData getMetaData();

    BreedDto selectBreedById(Integer breedId);

    boolean updateCat(CatDto catDto);

    List<BreedDto> findAllFromBreedWithFilter(BreedFilterDto breedFilterDto);
}
