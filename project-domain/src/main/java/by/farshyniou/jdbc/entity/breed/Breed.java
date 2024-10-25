package by.farshyniou.jdbc.entity.breed;

import by.farshyniou.jdbc.entity.cat.Cat;

import java.util.List;

public class Breed {
    private Long id;
    private String breedShort;
    private String name;
    private String temperament;
    private String origin;
    private String countryCode;
    private String description;
    private String lifeSpan;
    private String wikipediaUrl;
    private List<Cat> cats;

    public Breed() {
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

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    @Override
    public String toString() {
        return "Breed{" +
               "id=" + id +
               ", breedId='" + breedShort + '\'' +
               ", name='" + name + '\'' +
               ", temperament='" + temperament + '\'' +
               ", origin='" + origin + '\'' +
               ", countryCode='" + countryCode + '\'' +
               ", description='" + description + '\'' +
               ", lifeSpan='" + lifeSpan + '\'' +
               ", wikipediaUrl='" + wikipediaUrl + '\'' +
               ", cats=" + cats +
               '}';
    }
}
