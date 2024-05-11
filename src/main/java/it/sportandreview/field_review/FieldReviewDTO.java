package it.sportandreview.field_review;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.field.FieldDTO;
import it.sportandreview.game_match.GameMatchDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class FieldReviewDTO extends BaseDTO {

    private Long gameMatchId;
    private Long fieldId;
    private Long playerUserId;
    private Double value;
    private String note;
}
