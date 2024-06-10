package it.sportandreview.join_request;

import it.sportandreview.base.BaseDTO;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "il player è obbligatorio")
    private Long playerUserId;
    @NotNull(message = "la partita è obbligatoria")
    private Long gameMatchId;
    @NotNull(message = "lo stato della richiesta è obbligatorio")
    private Long joinRequestStateId;


}
