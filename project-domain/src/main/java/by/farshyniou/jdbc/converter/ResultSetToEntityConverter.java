package by.farshyniou.jdbc.converter;

import by.farshyniou.jdbc.entity.breed.Breed;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.utils.fieldname.breed.BreedName;
import by.farshyniou.jdbc.utils.fieldname.cat.CatName;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToEntityConverter {
    private ResultSetToEntityConverter() {
    }

    public static Cat convertToCat(ResultSet resultSet) throws SQLException {
        Cat cat = new Cat();
        cat.setId(resultSet.getLong(CatName.ID));
        cat.setCatId(resultSet.getString(CatName.CAT_ID));
        cat.setUrl(resultSet.getString(CatName.CAT_URL));
        Breed breed = new Breed();
        breed.setId(resultSet.getLong(4));
        breed.setBreedId(resultSet.getString(BreedName.BREED_ID));
        breed.setName(BreedName.BREED_NAME);
        breed.setDescription(BreedName.BREED_DESCRIPTION);
        breed.setTemperament(BreedName.BREED_TEMPERAMENT);
        breed.setOrigin(BreedName.BREED_ORIGIN);
        breed.setCountryCode(BreedName.BREED_COUNTRY_CODE);
        breed.setLifeSpan(BreedName.BREED_LIFE_SPAN);
        breed.setWikipediaUrl(BreedName.BREED_WIKIPEDIA_URL);
        return cat;
    }

    public static Breed convertToBreed(ResultSet resultSet) {
        return null;
    }


}
