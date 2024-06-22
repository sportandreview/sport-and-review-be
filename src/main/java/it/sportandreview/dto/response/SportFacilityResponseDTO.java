package it.sportandreview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportFacilityResponseDTO {
    private Long id;
    private String name;
    private AddressResponseDTO address;
    private Double rating;
}