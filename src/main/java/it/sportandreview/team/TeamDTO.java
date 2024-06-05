package it.sportandreview.team;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class TeamDTO extends BaseDTO {
    @NotBlank(message = "il nome del team è obbligatorio")
    private String name;
    @NotBlank(message = "il set di player è obbligatorio")
    private Set<PlayerUserDTO> players;
}
