package by.farshyniou.jdbc.converter.to_entity;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.breed.Breed;
import by.farshyniou.jdbc.entity.cat.Cat;
import by.farshyniou.jdbc.entity.filter.breed.BreedFilter;
import by.farshyniou.jdbc.filter.BreedFilterDto;

public class ToEntityConverter {

    public static Cat toCatEntity(CatDto catDto) {
        Cat cat = new Cat();
        cat.setCatId(catDto.getCatId());
        cat.setUrl(catDto.getUrl());
        cat.setBreed(ToEntityConverter.toBreedEntity(catDto.getBreedDto()));
        if (catDto.getId() != null) {
            cat.setId(catDto.getId());
        }
        return cat;
    }


    public static Breed toBreedEntity(BreedDto breedDto) {
        Breed breed = new Breed();
        breed.setId(breedDto.getId());
        breed.setBreedShort(breedDto.getBreedShort());
        breed.setName(breedDto.getName());
        breed.setTemperament(breedDto.getTemperament());
        breed.setOrigin(breedDto.getOrigin());
        breed.setCountryCode(breedDto.getCountryCode());
        breed.setDescription(breedDto.getDescription());
        breed.setLifeSpan(breedDto.getLifeSpan());
        breed.setWikipediaUrl(breedDto.getWikipediaUrl());
        return breed;
    }

    public static BreedFilter toBreedFilterEntity(BreedFilterDto breedFilterDto) {
        return new BreedFilter(breedFilterDto.getLimit(),
                breedFilterDto.getOffset(),
                breedFilterDto.getBreed_name(),
                breedFilterDto.getBreedId(),
                breedFilterDto.getCountryCode(),
                breedFilterDto.getBreed_temperament());
    }
}
