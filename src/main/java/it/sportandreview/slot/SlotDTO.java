package it.sportandreview.slot;

import it.sportandreview.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SlotDTO extends BaseDTO {

    private LocalTime time;
    private LocalTime durationSlot;
    private Long planningId;
}
