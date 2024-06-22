package it.sportandreview.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlotRequestDTO {
    @NotNull(message = "{validation.startTime.required}")
    private LocalDateTime startTime;

    @NotNull(message = "{validation.endTime.required}")
    private LocalDateTime endTime;

    private boolean available;
}
