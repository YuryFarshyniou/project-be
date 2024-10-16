package by.farshyniou.jdbc.cat;


import by.farshyniou.jdbc.breed.BreedDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CatDto {
    private int id;
    private String url;
    private String catId;
    private List<BreedDto> breedsDto;

    public CatDto() {
    }

    @JsonCreator
    public CatDto(@JsonProperty("url") String url, @JsonProperty("id") String catId, @JsonProperty("breeds") List<BreedDto> breedsDto) {
        this.url = url;
        this.catId = catId;
        this.breedsDto = breedsDto;
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

    public List<BreedDto> getBreeds() {
        return breedsDto;
    }

    public void setBreeds(List<BreedDto> breedsDto) {
        this.breedsDto = breedsDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BreedDto> getBreedsDto() {
        return breedsDto;
    }

    public void setBreedsDto(List<BreedDto> breedsDto) {
        this.breedsDto = breedsDto;
    }
}
