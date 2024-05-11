package it.sportandreview.auto_evaluation_sport;

import it.sportandreview.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AutoEvaluationSportLevelDTO extends BaseDTO {

    private Long autoEvaluationId;
    private Long gameLevelId;
    private Long sportId;
    private Double level;
    private Boolean competitiveLevel;
    private String numberOfTrainings;
}
