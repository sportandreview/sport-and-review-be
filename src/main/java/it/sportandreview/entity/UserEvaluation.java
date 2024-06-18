package it.sportandreview.entity;

import it.sportandreview.enums.SkillLevel;
import it.sportandreview.enums.TrainingFrequency;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_evaluation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "training_frequency", nullable = false)
    private TrainingFrequency trainingFrequency;

    @Column(name = "played_competitively", nullable = false)
    private Boolean playedCompetitively;
}
