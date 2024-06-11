package it.sportandreview.game_match;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.booked_slot.BookedSlot;
import it.sportandreview.enums.GenderType;
import it.sportandreview.field.Field;
import it.sportandreview.game_level.GameLevel;
import it.sportandreview.match_state.MatchState;
import it.sportandreview.payment.Payment;
import it.sportandreview.services.Services;
import it.sportandreview.team.Team;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_game_match")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GameMatch extends IndexedEntity {

    @ToString.Exclude
    @OneToMany(mappedBy = "gameMatch", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<BookedSlot> bookedSlots = new HashSet<>();

    @Column(name = "requested_date")
    private ZonedDateTime requestedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", referencedColumnName = "id")
    @ToString.Exclude
    private User organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    @ToString.Exclude
    private Field field;

    @Enumerated(EnumType.STRING)
    private GenderType genderType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ToString.Exclude
    private MatchState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_level_id", referencedColumnName = "id")
    @ToString.Exclude
    private GameLevel gameLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winning_team_id", referencedColumnName = "id")
    @ToString.Exclude
    private Team winningTeam;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_game_match_team",
            inverseJoinColumns = @JoinColumn(name = "team_id"),
            joinColumns = @JoinColumn(name = "game_match_id"))
    private Set<Team> teams = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_services_game_match",
            inverseJoinColumns = @JoinColumn(name = "services_id"),
            joinColumns = @JoinColumn(name = "game_match_id"))
    private Set<Services> services = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @Column(name = "total_players")
    private Integer totalPlayers;
}
