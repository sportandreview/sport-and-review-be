package it.sportandreview.invitation;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.invitation_state.InvitationState;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Entity
@Table(name = "app_invitation")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Invitation extends IndexedEntity {

    @Column(name = "date")
    private ZonedDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_match_id", referencedColumnName = "id")
    @ToString.Exclude
    private GameMatch gameMatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invitation_state_id", referencedColumnName = "id")
    @ToString.Exclude
    private InvitationState invitationState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User playerUser;

}
