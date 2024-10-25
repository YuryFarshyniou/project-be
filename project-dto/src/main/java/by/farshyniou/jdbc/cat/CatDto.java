package by.farshyniou.jdbc.cat;

import by.farshyniou.jdbc.breed.BreedDto;

public class CatDto {
    private Long id;
    private String catId;
    private String url;
    private BreedDto breedDto;

    public CatDto() {
    }

    public CatDto(String catId, String url, BreedDto breedDto) {
        this.catId = catId;
        this.url = url;
        this.breedDto = breedDto;
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

    public BreedDto getBreedDto() {
        return breedDto;
    }

    public void setBreedDto(BreedDto breedDto) {
        this.breedDto = breedDto;
    }

    @Override
    public String toString() {
        return "CatDto{" +
               "id=" + id +
               ", catId='" + catId + '\'' +
               ", url='" + url + '\'' +
               ", breedDto=" + breedDto +
               '}';
    }
}
