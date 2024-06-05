package it.sportandreview.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Il campo email è obbligatorio")
    private String email;

    @NotBlank(message = "Il campo password è obbligatorio")
    private String password;

    private String refreshToken;
}
