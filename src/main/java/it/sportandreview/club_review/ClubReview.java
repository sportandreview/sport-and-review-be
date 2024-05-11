package it.sportandreview.club_review;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.club.Club;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "app_club_review")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClubReview extends IndexedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User playerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    @ToString.Exclude
    private Club club;

    @Column(name = "customer_services")
    private Double customerServices;

    @Column(name = "locker_room")
    private Double lockerRoom;

    @Column(name = "services")
    private Double services;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_match_id", referencedColumnName = "id")
    @ToString.Exclude
    private GameMatch gameMatch;

    @Column(name = "voting_date")
    private LocalDate votingDate;

    @Column(name = "voting_time")
    private LocalTime votingTime;

}
