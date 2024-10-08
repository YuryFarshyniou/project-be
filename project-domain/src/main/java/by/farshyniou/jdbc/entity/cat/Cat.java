package by.farshyniou.jdbc.entity.cat;

import  by.farshyniou.jdbc.entity.breed.Breed;

import java.util.List;

public class Cat {
    private long id;
    private String url;
    private String apiId;
    private List<Breed> breeds;

    public Cat() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public List<Breed> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<Breed> breeds) {
        this.breeds = breeds;
    }

    public long getId() {
        return id;
    }
}
