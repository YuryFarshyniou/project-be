package by.farshyniou.jdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcRepository {

    void createCatTable() throws SQLException;

    void createBreedTable() throws SQLException;

    void createTables() throws SQLException;

    void insertIntoTables() throws SQLException;

    void insertIntoCatTable() throws SQLException;

    void deleteFromCatTable() throws SQLException;

    void selectAllFromCatTable();

    void dropTables() throws SQLException;

}
