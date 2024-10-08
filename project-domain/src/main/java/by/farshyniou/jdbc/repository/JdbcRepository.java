package by.farshyniou.jdbc.repository;

import java.sql.SQLException;

public interface JdbcRepository {

    void createCatTable() throws SQLException;

    void createBreedTable() throws SQLException;

    void createTables() throws SQLException;



}
