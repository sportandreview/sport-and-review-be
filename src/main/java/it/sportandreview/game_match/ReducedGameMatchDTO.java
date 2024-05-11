package it.sportandreview.game_match;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.booked_slot.BookedSlotDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ReducedGameMatchDTO extends BaseDTO {

    private Long fieldId;
    @Builder.Default
    private Set<BookedSlotDTO> bookedSlots = new HashSet<>();
    private Integer totalPlayers;
    private Long genderTeamId;
    @Builder.Default
    private Set<Long> servicesClub = new HashSet<>();


}
