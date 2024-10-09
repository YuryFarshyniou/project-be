package by.farshyniou.service.jdbc;

public interface JdbcService {

    void createCatTable();

    void createBreedTable();

    void createTables();

    void insertIntoTables();

    void insertIntoCatTable();

    void deleteFromCatTable();

    void dropTables();

    void selectAllFromCatTable();
}
