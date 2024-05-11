package it.sportandreview.game_level;

import it.sportandreview.base.IndexedEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;



@Entity
@Table(name = "app_game_level")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GameLevel extends IndexedEntity {

    @Column(name = "description")
    private String description;
}
