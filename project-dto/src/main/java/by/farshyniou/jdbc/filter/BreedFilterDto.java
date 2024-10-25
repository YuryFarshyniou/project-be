package by.farshyniou.jdbc.filter;

public class BreedFilterDto extends EntityFilterDto {

    private final String breed_name;
    private final String breedId;
    private final String countryCode;
    private final String breed_temperament;



    public BreedFilterDto(int limit, int offset, String breed_name, String breedId, String countryCode, String breedTemperament) {
        super(limit, offset);
        this.breed_name = breed_name;
        this.breedId = breedId;
        this.countryCode = countryCode;
        breed_temperament = breedTemperament;
    }

    public String getBreed_name() {
        return breed_name;
    }

    public String getBreedId() {
        return breedId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getBreed_temperament() {
        return breed_temperament;
    }
}
