package it.sportandreview.game_match;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookingGameMatchDTO {

    private Long fieldId;
    private LocalDate date;
    private Set<Long> services = new HashSet<>();
    private Double amount;
    private Long paymentTypeId;
    private Long playerUserId;
    @Builder.Default
    private Set<Long> slots = new HashSet<>();
}
