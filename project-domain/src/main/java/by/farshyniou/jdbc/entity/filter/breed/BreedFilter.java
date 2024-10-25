package by.farshyniou.jdbc.entity.filter.breed;

import by.farshyniou.jdbc.entity.filter.EntityFilter;

public class BreedFilter extends EntityFilter {
    private final String breedName;
    private final String breedId;
    private final String countryCode;
    private final String breedTemperament;


    public BreedFilter(int limit, int offset, String breed_name, String breedId, String countryCode, String breedTemperament) {
        super(limit, offset);
        this.breedName = breed_name;
        this.breedId = breedId;
        this.countryCode = countryCode;
        this.breedTemperament = breedTemperament;
    }

    public String getBreedName() {
        return breedName;
    }

    public String getBreedId() {
        return breedId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getBreedTemperament() {
        return breedTemperament;
    }
}
