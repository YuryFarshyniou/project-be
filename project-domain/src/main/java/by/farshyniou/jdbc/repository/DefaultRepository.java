package by.farshyniou.jdbc.repository;

import by.farshyniou.jdbc.cat_api.CatApi;

import java.util.List;

public interface DefaultRepository {

    void insertIntoTablesFromApi(List<CatApi> cats);

    void createTables();

    void insertIntoTablesExample();

    void dropTables();

}
