package by.farshyniou.jdbc.init.impl;


import by.farshyniou.jdbc.cat_api.CatApi;
import by.farshyniou.jdbc.exceptions.FetchingDataException;
import by.farshyniou.jdbc.init.Initiator;
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
    public List<CatApi> initiate() {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {

            HttpRequest apiRequest = HttpRequest.newBuilder(
                            URI.create(this.returnUri()))
                    .header("x-api-key", API_KEY)
                    .build();
            HttpResponse<String> apiResponse = httpClient.send(apiRequest, HttpResponse.BodyHandlers.ofString());
            String responseBody = apiResponse.body();
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            CatApi[] catApis = mapper.readValue(responseBody, CatApi[].class);
            return Arrays.asList(catApis);
        } catch (IOException | InterruptedException e) {
            LOGGER.warn("Exception occurred during fetching data from API. {}", e.getMessage());
            throw new FetchingDataException(e.getMessage());
        }
    }

    private String returnUri() {
        return MessageFormat.format(API_URI, "20", "1");
    }
}
