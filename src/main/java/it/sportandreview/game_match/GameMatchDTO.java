package it.sportandreview.game_match;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.booked_slot.BookedSlotDTO;
import it.sportandreview.field.FieldDTO;
import it.sportandreview.game_level.GameLevelDTO;
import it.sportandreview.gender_type.GenderTypeDTO;
import it.sportandreview.match_state.MatchStateDTO;
import it.sportandreview.payment.PaymentDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import it.sportandreview.services.ServicesDTO;
import it.sportandreview.team.TeamDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GameMatchDTO extends BaseDTO {

    private LocalDate date;
    private ZonedDateTime requestedDate;
    private PlayerUserDTO organizer;
    private FieldDTO field;
    private GenderTypeDTO genderTeam;
    private MatchStateDTO state;
    private GameLevelDTO gameLevel;
    private TeamDTO winningTeam;
    @Builder.Default
    private Set<TeamDTO> teams = new HashSet<>();
    @Builder.Default
    private Set<ServicesDTO> services = new HashSet<>();
    private PaymentDTO payment;
    @Builder.Default
    private Set<Long> slots = new HashSet<>();
    @Builder.Default
    private Set<BookedSlotDTO> bookedSlots = new HashSet<>();
    private Integer totalPlayers;

}
