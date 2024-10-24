package by.farshyniou.jdbc.entity.cat;

public class Cat {
    private Long id;
    private String url;
    private String catId;
    private Long breedId;

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

    public long getBreedId() {
        return breedId;
    }

    public void setBreedId(long breedId) {
        this.breedId = breedId;
    }

    @Override
    public String toString() {
        return "Cat{" +
               "id=" + id +
               ", url='" + url + '\'' +
               ", catId='" + catId + '\'' + '}';
    }
}
