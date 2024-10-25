package by.farshyniou.jdbc.converter.to_dto;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.cat.CatDto;
import by.farshyniou.jdbc.entity.breed.Breed;
import by.farshyniou.jdbc.entity.cat.Cat;

public final class ToDtoConverter {
    private ToDtoConverter() {
    }

    public static BreedDto convertToBreedDto(Breed breed) {
        BreedDto breedDto = new BreedDto();
        breedDto.setId(breed.getId());
        breedDto.setName(breed.getName());
        breedDto.setBreedShort(breed.getBreedShort());
        breedDto.setDescription(breed.getDescription());
        breedDto.setCountryCode(breed.getCountryCode());
        breedDto.setLifeSpan(breed.getLifeSpan());
        breedDto.setOrigin(breed.getOrigin());
        breedDto.setTemperament(breed.getTemperament());
        breedDto.setWikipediaUrl(breed.getWikipediaUrl());
        return breedDto;
    }


    public static CatDto convertToCatDto(Cat cat) {
        CatDto catDto = new CatDto();
        catDto.setId(cat.getId());
        catDto.setCatId(cat.getCatId());
        catDto.setUrl(cat.getUrl());
        catDto.setBreedDto(ToDtoConverter.convertToBreedDto(cat.getBreed()));
        return catDto;
    }

}
