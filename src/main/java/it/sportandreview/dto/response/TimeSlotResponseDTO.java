package it.sportandreview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlotResponseDTO {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean available;
}
