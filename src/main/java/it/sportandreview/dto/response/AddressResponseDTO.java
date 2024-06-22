package it.sportandreview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponseDTO {
    private Long id;
    private String street;
    private String streetNumber;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}