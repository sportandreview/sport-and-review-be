package it.sportandreview.field_review;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.field.Field;
import it.sportandreview.game_match.GameMatch;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "app_field_review")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FieldReview extends IndexedEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_match_id", referencedColumnName = "id")
    @ToString.Exclude
    private GameMatch gameMatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id", referencedColumnName = "id")
    @ToString.Exclude
    private Field field;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User playerUser;

    @Column(name = "value")
    private Double value;

    @Column(name = "note")
    private String note;


}
