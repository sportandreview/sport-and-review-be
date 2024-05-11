package it.sportandreview.team;

import it.sportandreview.base.BaseDTO;
import it.sportandreview.player_user.PlayerUserDTO;
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

    private String name;
    private Set<PlayerUserDTO> players;
}
