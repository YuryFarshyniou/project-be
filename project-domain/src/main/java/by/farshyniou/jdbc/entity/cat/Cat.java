package by.farshyniou.jdbc.entity.cat;

import  by.farshyniou.jdbc.entity.breed.Breed;

import java.util.List;

public class Cat {
    private int id;
    private String url;
    private String catId;
    private int breedId;

    public Cat() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBreedId() {
        return breedId;
    }

    public void setBreedId(int breedId) {
        this.breedId = breedId;
    }

    @Override
    public String toString() {
        return "Cat{" +
               "id=" + id +
               ", url='" + url + '\'' +
               ", catId='" + catId + '\'' +'}';
    }
}
