package it.sportandreview.dto.response;

import it.sportandreview.entity.SportAssessment;
import it.sportandreview.enums.GenderType;
import it.sportandreview.enums.RoleType;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String nickname;
    private String name;
    private String surname;
    private ZonedDateTime birthDate;
    private GenderType genderType;
    private String email;
    private String mobilePhone;
    private RoleType roleType;
    private boolean mobilePhoneCheck;
    private boolean emailCheck;
}
