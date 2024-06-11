package it.sportandreview.game_match;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.booked_slot.BookedSlotDTO;
import it.sportandreview.enums.GenderType;
import it.sportandreview.field.FieldDTO;
import it.sportandreview.game_level.GameLevelDTO;
import it.sportandreview.match_state.MatchStateDTO;
import it.sportandreview.payment.PaymentDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import it.sportandreview.services.ServicesDTO;
import it.sportandreview.team.TeamDTO;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "la data è obbligatoria!")
    private LocalDate date;
    private ZonedDateTime requestedDate;
    @NotNull(message = "l'organizzatore è obbligatorio!")
    private PlayerUserDTO organizer;
    @NotNull(message = "il campo è obbligatorio!")
    private FieldDTO field;
    @NotNull(message = "il genere del team è obbligatorio!")
    private GenderType genderTeam;
    @NotNull(message = "lo stato della partita è obbligatorio!")
    private MatchStateDTO state;
    @NotNull(message = "il livello di gioco è obbligatorio!")
    private GameLevelDTO gameLevel;
    @NotNull(message = "il team vincitore è obbligatorio!")
    private TeamDTO winningTeam;
    @NotNull(message = "i teams sono obbligatori!")
    @Builder.Default
    private Set<TeamDTO> teams = new HashSet<>();
    @Builder.Default
    private Set<ServicesDTO> services = new HashSet<>();
    @NotNull(message = "il pagamento è obbligatorio!")
    private PaymentDTO payment;
    @NotNull(message = "gli slot sono obbligatori!")
    @Builder.Default
    private Set<Long> slots = new HashSet<>();
    @Builder.Default
    private Set<BookedSlotDTO> bookedSlots = new HashSet<>();
    private Integer totalPlayers;

}
