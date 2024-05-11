package it.sportandreview.auto_evaluation;

import it.sportandreview.auto_evaluation_sport.AutoEvaluationSportLevelDTO;
import it.sportandreview.base.BaseDTO;
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

    private Long playerUserId;
    private Double physicalLevel;
    private Double weight;
    private Double height;
    private Integer physicalStructure;
    private Double bmi;
    @Builder.Default
    private Set<AutoEvaluationSportLevelDTO> sportLevels = new HashSet<>();
}
