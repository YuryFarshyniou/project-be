package by.farshyniou;

import by.farshyniou.service.jdbc.JdbcService;
import by.farshyniou.service.jdbc.impl.JdbcServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppEntryPoint.class);

    public static void main(String[] args) {
        JdbcService jdbcService = new JdbcServiceImpl();
        jdbcService.selectAllFromCatTable();

//        jdbcService.createTables();
//        Initiator initiator = new InitiatorImpl();
//        initiator.initiate();

    }

}