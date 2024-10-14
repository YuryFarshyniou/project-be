package by.farshyniou.jdbc.converter.to_entity;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.breed.Breed;
import by.farshyniou.jdbc.entity.cat.Cat;

public class ToEntityConverter {

    public static Cat toCatEntity(CatDto catDto) {
        Cat cat = new Cat();
        cat.setCatId(catDto.getCatId());
        cat.setUrl(catDto.getUrl());
        BreedDto breedDto = catDto.getBreeds().getFirst();
        return cat;
    }


    public static Breed toBreedEntity(BreedDto breedDto) {
        Breed breed = new Breed();
        breed.setId(breedDto.getId());
        breed.setBreedId(breedDto.getBreedId());
        breed.setName(breedDto.getName());
        breed.setTemperament(breedDto.getTemperament());
        breed.setOrigin(breedDto.getOrigin());
        breed.setCountryCode(breedDto.getCountryCode());
        breed.setDescription(breedDto.getDescription());
        breed.setLifeSpan(breedDto.getLifeSpan());
        breed.setWikipediaUrl(breedDto.getWikipediaUrl());

        return breed;
    }
}
