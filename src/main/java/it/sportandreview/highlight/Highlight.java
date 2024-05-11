package it.sportandreview.highlight;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Entity
@Table(name = "app_highlight")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Highlight extends IndexedEntity {

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;

    @Column(name = "thumbnail")
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_match_id", referencedColumnName = "id")
    @ToString.Exclude
    private GameMatch gameMatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User createdBy;

}
