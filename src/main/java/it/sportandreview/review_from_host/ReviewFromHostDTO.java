package it.sportandreview.review_from_host;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.owner.OwnerDTO;
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
public class ReviewFromHostDTO extends BaseDTO {

    private PlayerUserDTO playerUser;
    private OwnerDTO owner;
    private Double behavior;
}
