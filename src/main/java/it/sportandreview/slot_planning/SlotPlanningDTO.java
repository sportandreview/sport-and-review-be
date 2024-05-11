package it.sportandreview.slot_planning;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.field.FieldDTO;
import it.sportandreview.slot.SlotDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class SlotPlanningDTO extends BaseDTO {

    private String daysOfWeek;
    private LocalTime durationSlot;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private LocalDate startDateValidity;
    private LocalDate endDateValidity;
    private Long fieldId;
    @Builder.Default
    private Set<SlotDTO> slots = new HashSet<>();

}
