package it.sportandreview.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequest {

    @NotBlank(message = "id user obbligatorio!")
    private Long id;
    @NotBlank(message = "vecchia password obbligatorio!")
    private String oldPassword;
    @NotBlank(message = "nuova password obbligatorio!")
    private String newPassword;

}
