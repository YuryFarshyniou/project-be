package by.farshyniou.jdbc.converter.to_dto;

import by.farshyniou.jdbc.breed.BreedDto;
import by.farshyniou.jdbc.entity.breed.Breed;

public final class ToDtoConverter {
    private ToDtoConverter() {}

    public static BreedDto convertToBreedDto(Breed breed) {
        BreedDto breedDto = new BreedDto();
        breedDto.setId(breed.getId());
        breedDto.setName(breed.getName());
        breedDto.setBreedId(breed.getBreedId());
        breedDto.setDescription(breed.getDescription());
        breedDto.setCountryCode(breed.getCountryCode());
        breedDto.setLifeSpan(breed.getLifeSpan());
        breedDto.setOrigin(breed.getOrigin());
        breedDto.setTemperament(breed.getTemperament());
        breedDto.setWikipediaUrl(breed.getWikipediaUrl());
        return breedDto;
    }
}
