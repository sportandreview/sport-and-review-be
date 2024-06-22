package it.sportandreview.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class GooglePlaceDetailsResponse {
    private Result result;
    private String status;

    @Data
    public static class Result {
        private String formatted_address;
        private Geometry geometry;
        private String name;
        private List<AddressComponent> address_components;

        @Data
        public static class Geometry {
            private Location location;

            @Data
            public static class Location {
                private double lat;
                private double lng;
            }
        }

        @Data
        public static class AddressComponent {
            private String long_name;
            private String short_name;
            private List<String> types;
        }
    }
}
