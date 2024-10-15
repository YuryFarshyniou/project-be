package by.farshyniou.jdbc.repository;

import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.cat.Cat;


import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

public interface JdbcRepository {

    void createCatTable() throws SQLException;

    void createBreedTable() throws SQLException;

    void createTables() throws SQLException;

    void insertIntoTablesExample() throws SQLException;

    void deleteFromCatTable() throws SQLException;

    List<Cat> selectAllFromCatTable();

    void dropTables() throws SQLException;

    void insertIntoTablesFromApi(List<CatDto> cats) throws SQLException;

    DatabaseMetaData getMetaData() throws SQLException;


}
