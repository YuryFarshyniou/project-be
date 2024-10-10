package by.farshyniou.jdbc.entity.breed;

import by.farshyniou.jdbc.entity.cat.Cat;

import java.util.List;

public class Breed {
    private long id;
    private String breedId;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
               ", breedId='" + breedId + '\'' +
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
