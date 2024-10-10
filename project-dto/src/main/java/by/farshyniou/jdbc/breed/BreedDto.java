package by.farshyniou.jdbc.breed;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BreedDto {
    private long id;
    private String breedId;
    private String name;
    private String temperament;
    private String origin;
    private String countryCode;
    private String description;
    private String lifeSpan;
    private String wikipediaUrl;

    public BreedDto() {
    }

    @JsonCreator
    public BreedDto(@JsonProperty("id") String breedId, @JsonProperty("name") String name, @JsonProperty("temperament") String temperament, @JsonProperty("origin")
    String origin, @JsonProperty("country_code") String countryCode, @JsonProperty("description") String description, @JsonProperty("life_span") String lifeSpan, @JsonProperty("wikipedia_url")
                 String wikipediaUrl) {
        this.breedId = breedId;
        this.name = name;
        this.temperament = temperament;
        this.origin = origin;
        this.countryCode = countryCode;
        this.description = description;
        this.lifeSpan = lifeSpan;
        this.wikipediaUrl = wikipediaUrl;
    }

    public String getBreedId() {
        return breedId;
    }

    public void setBreedId(String breedId) {
        this.breedId = breedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }
}
