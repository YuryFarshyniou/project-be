package by.farshyniou.init;


import by.farshyniou.cat.Cat;
import by.farshyniou.exceptions.FetchingDataException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitiatorImpl implements Initiator {
    public static final Logger LOGGER = LoggerFactory.getLogger(InitiatorImpl.class);
    public static String API_KEY = "live_MhozaHEHUhNwixAP8DtE90qiFywvn0d6mBtLeFiDOZSsDSQJWvtoidut3NLEHXlR";
    public static String API_URI = "https://api.thecatapi.com/v1/images/search?limit={0}&has_breeds={1}";

    @Override
    public List<Cat> initiate() {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {

            HttpRequest apiRequest = HttpRequest.newBuilder(
                            URI.create(this.returnUri()))
                    .header("x-api-key", API_KEY)
                    .build();
            HttpResponse<String> apiResponse = httpClient.send(apiRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = apiResponse.body();
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Cat[] cats = mapper.readValue(responseBody, Cat[].class);
            return Arrays.asList(cats);
        } catch (IOException | InterruptedException e) {
            LOGGER.warn("Exception occurred during fetching data from API. {}", e.getMessage());
            throw new FetchingDataException(e.getMessage());
        }
    }

    private String returnUri() {
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("limit", "20");
        uriParams.put("has_breeds", "1");
        return MessageFormat.format(API_URI, "20", "1", API_KEY);
    }
}
