package by.farshyniou.jdbc;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.cat.Cat;
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
        BreedDto breedDto = new BreedDto();
        breedDto.setId(5);
        CatDto catDto = new CatDto("hun", "hui_id", List.of(breedDto));
        catDto.setId(7);
        boolean b = JdbcServiceImpl.getInstance().updateCat(catDto);
        LOGGER.info(String.valueOf(b));
//        Initiator initiator = new InitiatorImpl();
//        List<CatDto> catsDto = initiator.initiate();
//        jdbcService.insertIntoTablesFromApi(catsDto);

    }

}