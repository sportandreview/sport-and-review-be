package it.sportandreview.auto_evaluation_sport;

import it.sportandreview.auto_evaluation.AutoEvaluation;
import it.sportandreview.base.IndexedEntity;
import it.sportandreview.game_level.GameLevel;
import it.sportandreview.sport.Sport;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "app_auto_evaluation_sport_level")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AutoEvaluationSportLevel extends IndexedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auto_evaluation_id", referencedColumnName = "id")
    @ToString.Exclude
    private AutoEvaluation autoEvaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_level_id", referencedColumnName = "id")
    @ToString.Exclude
    private GameLevel gameLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", referencedColumnName = "id")
    @ToString.Exclude
    private Sport sport;

    @Column(name = "level")
    private Double level;

    @Column(name = "competitive_level")
    private Boolean competitiveLevel;

    @Column(name = "number_of_trainings")
    private String numberOfTrainings;

}
