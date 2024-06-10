package it.sportandreview.slot_planning;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.slot.SlotDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "i giorni della settimana sono obbligatori!")
    private String daysOfWeek;
    @NotNull(message = "la durata è obbligatoria!")
    private LocalTime durationSlot;
    @NotNull(message = "l'orario di apertura è obbligatorio!")
    private LocalTime openingTime;
    @NotNull(message = "orario di chiusura è obbligatorio!")
    private LocalTime closingTime;
    @NotNull(message = "data inizio validità è obbligatoria!")
    private LocalDate startDateValidity;
    @NotNull(message = "data fine validità è obbligatoria!")
    private LocalDate endDateValidity;
    @NotNull(message = "il campo è obbligatorio!")
    private Long fieldId;
    @Builder.Default
    private Set<SlotDTO> slots = new HashSet<>();

}
