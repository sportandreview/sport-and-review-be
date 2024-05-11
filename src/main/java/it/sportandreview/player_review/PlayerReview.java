package it.sportandreview.player_review;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.sport.Sport;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "app_player_review")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PlayerReview extends IndexedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User playerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "made_by_player_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User madeBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_match_id", referencedColumnName = "id")
    @ToString.Exclude
    private GameMatch gameMatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    @ToString.Exclude
    private Sport sport;

    @Column(name = "physical_ability")
    private Double physicalAbility;

    @Column(name = "behavior")
    private Double behavior;

    @Column(name = "tactical_ability")
    private Double tacticalAbility;

    @Column(name = "technical_ability")
    private Double technicalAbility;

    @Column(name = "voting_date")
    private LocalDate votingDate;

    @Column(name = "voting_time")
    private LocalTime votingTime;
}
