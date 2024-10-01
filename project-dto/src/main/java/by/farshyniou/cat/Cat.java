package by.farshyniou.cat;


import by.farshyniou.breed.Breed;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Cat {
    private String url;
    private String apiId;
    private List<Breed> breeds;

    public Cat() {
    }

    @JsonCreator
    public Cat(@JsonProperty("url") String url,@JsonProperty("id") String apiId,@JsonProperty("breeds") List<Breed> breeds) {
        this.url = url;
        this.apiId = apiId;
        this.breeds = breeds;
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
}
