package it.sportandreview.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Long userId;
    private String token;
    private String refreshToken;
    private boolean mobilePhoneCheck;
    private boolean emailCheck;


}
