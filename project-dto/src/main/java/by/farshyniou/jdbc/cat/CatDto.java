package by.farshyniou.jdbc.cat;

public class CatDto {
    private Long id;
    private String url;
    private String catId;
    private Long breedId;

    public CatDto() {
    }

    public CatDto(Long id, String url, String catId, Long breedId) {
        this.id = id;
        this.url = url;
        this.catId = catId;
        this.breedId = breedId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getBreedId() {
        return breedId;
    }

    public void setBreedId(Long breedId) {
        this.breedId = breedId;
    }
}
