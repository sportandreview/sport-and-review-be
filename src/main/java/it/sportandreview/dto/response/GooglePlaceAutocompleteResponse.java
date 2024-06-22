package it.sportandreview.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GooglePlaceAutocompleteResponse {
    private List<Prediction> predictions;
    private String status;

    @Data
    public static class Prediction {
        private String description;
        private String place_id;
    }
}
