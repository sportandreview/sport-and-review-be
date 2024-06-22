package it.sportandreview.service.impl;

import it.sportandreview.dto.response.GooglePlaceAutocompleteResponse;
import it.sportandreview.dto.response.GooglePlaceDetailsResponse;
import it.sportandreview.service.GoogleMapsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleMapsServiceImpl implements GoogleMapsService {

    private final RestTemplate restTemplate;

    @Value("${google.maps.api.key}")
    private String apiKey;

    private static final String AUTOCOMPLETE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json";
    private static final String DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json";

    @Override
    public GooglePlaceAutocompleteResponse getAutocompleteSuggestions(String input) {
        URI uri = UriComponentsBuilder.fromHttpUrl(AUTOCOMPLETE_URL)
                .queryParam("input", input)
                .queryParam("key", apiKey)
                .build()
                .toUri();
        log.info("apiKey: {}", apiKey);
        return restTemplate.getForObject(uri, GooglePlaceAutocompleteResponse.class);
    }

    @Override
    public GooglePlaceDetailsResponse getPlaceDetails(String placeId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(DETAILS_URL)
                .queryParam("placeid", placeId)
                .queryParam("key", apiKey)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, GooglePlaceDetailsResponse.class);
    }
}
