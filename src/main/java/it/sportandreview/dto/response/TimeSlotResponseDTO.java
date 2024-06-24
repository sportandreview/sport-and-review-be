package it.sportandreview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlotResponseDTO {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean available;
}
