package by.farshyniou.jdbc.repository;

import java.sql.DatabaseMetaData;
import java.util.List;
import java.util.Optional;

public interface JdbcRepository<K, E> {

    void createTable();

    Long delete(K id);

    List<E> findAll();

    DatabaseMetaData getMetaData();

    Optional<E> findById(K id);

    Boolean update(E entity);

    boolean insert(E entity);

}
