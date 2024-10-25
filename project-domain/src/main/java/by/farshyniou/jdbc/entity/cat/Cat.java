package by.farshyniou.jdbc.entity.cat;
import by.farshyniou.jdbc.entity.breed.Breed;


public class Cat {
    private Long id;
    private String url;
    private String catId;
    private Breed breed;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }


}
