package it.sportandreview.admin_user;

import it.sportandreview.sport.Sport;
import it.sportandreview.user.UserDTO;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Name structure is required")
    private String nameStructure;

    @NotBlank(message = "Address structure is required")
    private String addressStructure;

    @Builder.Default
    private Set<Sport> sports = new HashSet<>();

    private Integer numberFields;

}
