package it.sportandreview.auto_evaluation;

import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevelDTO;
import it.sportandreview.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AutoEvaluationDTO extends BaseDTO {
    @NotNull(message = "l'id del player è obbligatorio")
    private Long playerUserId;
    @NotNull(message = "il livello fisico è obbligatorio")
    private Double physicalLevel;
    private Double weight;
    private Double height;
    @NotNull(message = "la struttura fisica è obbligatorio")
    private Integer physicalStructure;
    private Double bmi;
    @Builder.Default
    private Set<AutoEvaluationSportLevelDTO> sportLevels = new HashSet<>();
}
