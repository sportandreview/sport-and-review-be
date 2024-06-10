package it.sportandreview.player_user;

import it.sportandreview.gender_type.GenderTypeDTO;
import it.sportandreview.team.TeamDTO;
import it.sportandreview.user.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PlayerUserDTO extends UserDTO {
    private Double physicalLevel;
    private Double technicalLevel;
    private Double tacticalLevel;
    private Double behavior;
    private Double technicalAbility;
    @Builder.Default
    private Set<Long> favoriteClubsId = new HashSet<>();
    @Builder.Default
    private Set<Long> favoriteFieldsId = new HashSet<>();
    @Builder.Default
    private Set<Long> friendsId = new HashSet<>();
    @Builder.Default
    private Set<Long> favoriteHighlightsId = new HashSet<>();
    @Builder.Default
    private Set<TeamDTO> teams = new HashSet<>();

}
