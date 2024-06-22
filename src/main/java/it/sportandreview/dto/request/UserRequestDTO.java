package it.sportandreview.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import it.sportandreview.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    @NotBlank(message = "{validation.nickname.required}")
    private String nickname;

    @NotBlank(message = "{validation.name.required}")
    private String name;

    @NotBlank(message = "{validation.surname.required}")
    private String surname;

    @NotNull(message = "{validation.birthdate.required}")
    private ZonedDateTime birthDate;

    @NotNull(message = "{validation.gender.required}")
    private String genderType;

    @NotBlank(message = "{validation.email.required}")
    @Email(message = "{validation.email.invalid}")
    private String email;

    @NotBlank(message = "{validation.password.required}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "{validation.password.invalid}")
    private String password;

    private String mobilePhone;

    @NotNull(message = "{validation.role.required}")
    private RoleType role;
}
