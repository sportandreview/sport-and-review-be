package it.sportandreview.auto_evaluation;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "app_auto_evaluation")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AutoEvaluation extends IndexedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_user_id", referencedColumnName = "id")
    @ToString.Exclude
    private User playerUser;

    @Column(name = "physical_level")
    private Double physicalLevel;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "height")
    private Double height;

    @Column(name = "physical_structure")
    private Integer physicalStructure;

    @Column(name = "bmi")
    private Double bmi;
}
