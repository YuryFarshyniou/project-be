package by.farshyniou.jdbc.entity.cat;

import  by.farshyniou.jdbc.entity.breed.Breed;

import java.util.List;

public class Cat {
    private long id;
    private String url;
    private String catId;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cat{" +
               "id=" + id +
               ", url='" + url + '\'' +
               ", catId='" + catId + '\'' +'}';
    }
}
