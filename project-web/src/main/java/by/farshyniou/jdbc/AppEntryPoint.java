package by.farshyniou.jdbc;

import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.init.Initiator;
import by.farshyniou.jdbc.init.impl.InitiatorImpl;
import by.farshyniou.jdbc.service.JdbcService;
import by.farshyniou.jdbc.service.impl.JdbcServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AppEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppEntryPoint.class);

    public static void main(String[] args) {
        JdbcService jdbcService = new JdbcServiceImpl();

        Initiator initiator = new InitiatorImpl();
        List<CatDto> initiate = initiator.initiate();
        jdbcService.insertIntoTablesFromApi(initiate);

    }

}