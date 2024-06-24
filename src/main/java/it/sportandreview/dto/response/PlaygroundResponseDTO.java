package it.sportandreview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaygroundResponseDTO {
    private Long id;
    private String name;
    private Double rating;
    private String surfaceType;
    private SportResponseDTO sport;
    private Set<TimeSlotResponseDTO> timeSlots;
}
