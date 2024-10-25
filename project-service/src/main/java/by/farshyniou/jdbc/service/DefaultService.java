package by.farshyniou.jdbc.service;

import by.farshyniou.jdbc.cat_api.CatApi;

import java.util.List;

public interface DefaultService {

    void insertIntoTablesFromApi(List<CatApi> cats);

    void createTables();

    void insertIntoTablesExample();

    void dropTables();

}
