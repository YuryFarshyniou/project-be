package by.farshyniou.jdbc.service;

import java.sql.DatabaseMetaData;
import java.util.List;

public interface JdbcService<K, E> {

    void createTable();

    Long delete(K id);

    List<E> findAll();

    DatabaseMetaData getMetaData();

    E findById(Long id);

    boolean update(E dto);

    boolean insert(E dto);

}
