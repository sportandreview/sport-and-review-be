package it.sportandreview.join_request;

import it.sportandreview.base.BaseDTO;
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
public class JoinRequestDTO extends BaseDTO {

    private Long playerUserId;
    private Long gameMatchId;
    private Long joinRequestStateId;


}
