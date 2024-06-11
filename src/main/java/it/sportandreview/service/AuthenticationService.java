package it.sportandreview.service;

import it.sportandreview.dto.request.AuthenticationRequestDTO;
import it.sportandreview.dto.request.UserRegistrationRequestDTO;
import it.sportandreview.dto.response.AuthenticationResponseDTO;
import it.sportandreview.enums.Role;
import it.sportandreview.user.User;

public interface AuthenticationService {
    void register(UserRegistrationRequestDTO userRegistrationRequestDTO);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
    AuthenticationResponseDTO refreshToken(AuthenticationRequestDTO request);
    AuthenticationResponseDTO generateTokens(User user);
    void updateVerificationStatus(String key, boolean b);
}
