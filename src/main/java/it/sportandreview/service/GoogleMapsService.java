package it.sportandreview.service;

import it.sportandreview.dto.response.GooglePlaceAutocompleteResponse;
import it.sportandreview.dto.response.GooglePlaceDetailsResponse;

public interface GoogleMapsService {
    GooglePlaceAutocompleteResponse getAutocompleteSuggestions(String input);
    GooglePlaceDetailsResponse getPlaceDetails(String placeId);
}
