package by.farshyniou.jdbc.breed;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BreedDto {
    private Long id;
    private String breedShort;
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
    public BreedDto(@JsonProperty("id") String breedShort, @JsonProperty("name") String name, @JsonProperty("temperament") String temperament, @JsonProperty("origin")
    String origin, @JsonProperty("country_code") String countryCode, @JsonProperty("description") String description, @JsonProperty("life_span") String lifeSpan, @JsonProperty("wikipedia_url")
                    String wikipediaUrl) {
        this.breedShort = breedShort;
        this.name = name;
        this.temperament = temperament;
        this.origin = origin;
        this.countryCode = countryCode;
        this.description = description;
        this.lifeSpan = lifeSpan;
        this.wikipediaUrl = wikipediaUrl;
    }

    public String getBreedShort() {
        return breedShort;
    }

    public void setBreedShort(String breedShort) {
        this.breedShort = breedShort;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BreedDto{" +
               "id=" + id +
               ", breedId='" + breedShort + '\'' +
               ", name='" + name + '\'' +
               ", temperament='" + temperament + '\'' +
               ", origin='" + origin + '\'' +
               ", countryCode='" + countryCode + '\'' +
               ", description='" + description + '\'' +
               ", lifeSpan='" + lifeSpan + '\'' +
               ", wikipediaUrl='" + wikipediaUrl + '\'' +
               '}';
    }
}
