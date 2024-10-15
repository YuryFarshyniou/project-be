package by.farshyniou.jdbc.service.impl;

import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.repository.JdbcRepository;
import by.farshyniou.jdbc.repository.impl.JdbcRepositoryImpl;
import by.farshyniou.jdbc.service.JdbcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

public class JdbcServiceImpl implements JdbcService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcServiceImpl.class);
    private JdbcRepository jdbcRepository;


    @Override
    public void createCatTable() {
        try {
            jdbcRepository = new JdbcRepositoryImpl();
            jdbcRepository.createCatTable();
        } catch (SQLException exception) {
            LOGGER.debug("Exception during creating CAT table {}", exception.getMessage());
        }

    }

    @Override
    public void createBreedTable() {
        try {
            jdbcRepository = new JdbcRepositoryImpl();
            jdbcRepository.createBreedTable();
        } catch (SQLException exception) {
            LOGGER.debug("Exception during creating BREED table {}", exception.getMessage());
        }
    }

    @Override
    public void createTables() {
        try {
            jdbcRepository = new JdbcRepositoryImpl();
            jdbcRepository.createTables();
        } catch (SQLException exception) {
            LOGGER.debug("Exception during creating tables {}", exception.getMessage());
        }

    }

    @Override
    public void insertIntoTables() {
        try {
            jdbcRepository = new JdbcRepositoryImpl();
            jdbcRepository.insertIntoTablesExample();
        } catch (SQLException exception) {
            LOGGER.debug("Exception during inserting into tables {}", exception.getMessage());
        }
    }

    @Override
    public void deleteFromCatTable() {
        try {
            jdbcRepository = new JdbcRepositoryImpl();
            jdbcRepository.deleteFromCatTable();
        } catch (SQLException exception) {
            LOGGER.debug("Exception during deleting CAT table {}", exception.getMessage());
        }
    }

    @Override
    public void dropTables() {
        try {
            jdbcRepository = new JdbcRepositoryImpl();
            jdbcRepository.dropTables();
        } catch (SQLException exception) {
            LOGGER.debug("Exception during dropping tables {}", exception.getMessage());
        }
    }

    @Override
    public List<Cat> selectAllFromCatTable() {
        jdbcRepository = new JdbcRepositoryImpl();
        return jdbcRepository.selectAllFromCatTable();
    }

    @Override
    public void insertIntoTablesFromApi(List<CatDto> cats) {
        jdbcRepository = new JdbcRepositoryImpl();
        try {
            jdbcRepository.insertIntoTablesFromApi(cats);
        } catch (SQLException exception) {
            LOGGER.debug("Exception during inserting into tables from Api {}", exception.getMessage());
        }
    }

    @Override
    public DatabaseMetaData getMetaData() {
       try {
           jdbcRepository = new JdbcRepositoryImpl();
           return jdbcRepository.getMetaData();
       } catch (SQLException exception) {
           LOGGER.debug("Exception during getting MetaData {}", exception.getMessage());
       }
       return null;
    }
}
