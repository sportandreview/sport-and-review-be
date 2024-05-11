package it.sportandreview.booked_slot;

import it.sportandreview.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class BookedSlotDTO extends BaseDTO {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long slotId;
    private Long fieldId;
    private Boolean allDay;
    private Long gameMatchId;

}
