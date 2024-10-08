package by.farshyniou.cat;


import by.farshyniou.breed.BreedDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CatDto {
    private String url;
    private String apiId;
    private List<BreedDto> breedsDto;

    public CatDto() {
    }

    @JsonCreator
    public CatDto(@JsonProperty("url") String url, @JsonProperty("id") String apiId, @JsonProperty("breeds") List<BreedDto> breedsDto) {
        this.url = url;
        this.apiId = apiId;
        this.breedsDto = breedsDto;
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

    public List<BreedDto> getBreeds() {
        return breedsDto;
    }

    public void setBreeds(List<BreedDto> breedsDto) {
        this.breedsDto = breedsDto;
    }
}
