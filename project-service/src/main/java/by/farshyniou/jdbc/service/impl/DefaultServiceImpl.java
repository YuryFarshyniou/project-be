package by.farshyniou.jdbc.service.impl;

import by.farshyniou.jdbc.cat_api.CatApi;
import by.farshyniou.jdbc.repository.impl.DefaultRepositoryImpl;
import by.farshyniou.jdbc.service.DefaultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefaultServiceImpl implements DefaultService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultServiceImpl.class);
    private static final DefaultService INSTANCE = new DefaultServiceImpl();

    private DefaultServiceImpl() {
    }

    public static DefaultService getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertIntoTablesFromApi(List<CatApi> cats) {
        DefaultRepositoryImpl.getInstance().insertIntoTablesFromApi(cats);
    }

    @Override
    public void createTables() {
        DefaultRepositoryImpl.getInstance().createTables();
    }

    @Override
    public void insertIntoTablesExample() {
        DefaultRepositoryImpl.getInstance().insertIntoTablesExample();
    }

    @Override
    public void dropTables() {
        DefaultRepositoryImpl.getInstance().dropTables();
    }
}
