package it.sportandreview.admin_user;

import it.sportandreview.sport.Sport;
import it.sportandreview.user.UserDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class AdminUserDTO extends UserDTO {

    @ToString.Exclude
    private String nameStructure;
    private String addressStructure;
    @Builder.Default
    private Set<Sport> sports = new HashSet<>();
    private Integer numberFields;

}
