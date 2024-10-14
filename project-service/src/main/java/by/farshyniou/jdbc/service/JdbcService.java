package by.farshyniou.jdbc.service;

import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.cat.Cat;

import java.util.List;

public interface JdbcService {

    void createCatTable();

    void createBreedTable();

    void createTables();

    void insertIntoTables();

    void insertIntoCatTable();

    void deleteFromCatTable();

    void dropTables();

    List<Cat> selectAllFromCatTable();

    void insertIntoTablesFromApi(List<CatDto> cats);
}
