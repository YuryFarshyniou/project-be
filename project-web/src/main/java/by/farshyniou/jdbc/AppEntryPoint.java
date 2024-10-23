package by.farshyniou.jdbc;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.filter.BreedFilterDto;
import by.farshyniou.jdbc.init.Initiator;
import by.farshyniou.jdbc.init.impl.InitiatorImpl;
import by.farshyniou.jdbc.service.JdbcService;
import by.farshyniou.jdbc.service.impl.JdbcServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DatabaseMetaData;
import java.util.List;

public class AppEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppEntryPoint.class);

    public static void main(String[] args) {
        BreedFilterDto breedFilterDto = new BreedFilterDto(2,
                1,
                null,
                null,
                null,
                null);
        List<BreedDto> allFromBreedWithFilter = JdbcServiceImpl.getInstance().findAllFromBreedWithFilter(breedFilterDto);
        allFromBreedWithFilter.forEach(System.out::println);
//        Initiator initiator = new InitiatorImpl();
//        List<CatDto> catsDto = initiator.initiate();
//        jdbcService.insertIntoTablesFromApi(catsDto);

    }

}