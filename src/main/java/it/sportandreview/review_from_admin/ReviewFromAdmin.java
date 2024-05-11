package it.sportandreview.review_from_admin;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.owner.Owner;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Table(name = "app_review_from_Admin")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ReviewFromAdmin extends IndexedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User playerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User adminUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_match_id", referencedColumnName = "id")
    @ToString.Exclude
    private GameMatch gameMatch;

    @Column(name = "date_and_time_review")
    private LocalDateTime dateAndTimeReview;

    @Column(name = "booking")
    private Double booking;

    @Column(name = "behavior_race")
    private Double behaviorRace;
}
