package by.farshyniou.service.jdbc.impl;

import by.farshyniou.jdbc.repository.JdbcRepository;
import by.farshyniou.jdbc.repository.impl.JdbcRepositoryImpl;
import by.farshyniou.service.jdbc.JdbcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

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
            jdbcRepository.insertIntoTables();
        } catch (SQLException exception) {
            LOGGER.debug("Exception during inserting into tables {}", exception.getMessage());
        }
    }

    @Override
    public void insertIntoCatTable() {
        try {
            jdbcRepository = new JdbcRepositoryImpl();
            jdbcRepository.insertIntoCatTable();
        } catch (SQLException exception) {
            LOGGER.debug("Exception during inserting into cat table {}", exception.getMessage());
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
    public void selectAllFromCatTable() {
        jdbcRepository = new JdbcRepositoryImpl();
        jdbcRepository.selectAllFromCatTable();
    }
}
