package by.farshyniou.jdbc;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat_api.CatApi;
import by.farshyniou.jdbc.filter.BreedFilterDto;
import by.farshyniou.jdbc.init.Initiator;
import by.farshyniou.jdbc.init.impl.InitiatorImpl;
import by.farshyniou.jdbc.service.impl.BreedJdbcServiceImpl;
import by.farshyniou.jdbc.service.impl.DefaultServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AppEntryPoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppEntryPoint.class);

    public static void main(String[] args) {

        BreedFilterDto breedFilterDto = new BreedFilterDto(4,
                1,
                null,
                null,
                null,
                "Loyal");
        List<BreedDto> allFromBreedWithFilter = BreedJdbcServiceImpl.getInstance().findAllFromBreedWithFilter(breedFilterDto);
        allFromBreedWithFilter.forEach(System.out::println);
//        DefaultServiceImpl.getInstance().createTables();
//        Initiator initiator = new InitiatorImpl();
//        List<CatApi> catApis = initiator.initiate();
//        DefaultServiceImpl.getInstance().insertIntoTablesFromApi(catApis);

    }

}