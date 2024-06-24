package it.sportandreview.dto.request;

import jakarta.validation.constraints.NotNull;
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
public class TimeSlotRequestDTO {
    @NotNull(message = "{validation.startTime.required}")
    private LocalTime startTime;

    @NotNull(message = "{validation.endTime.required}")
    private LocalTime endTime;

    private boolean available;
}
