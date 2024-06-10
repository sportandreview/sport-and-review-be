package it.sportandreview.service;

import it.sportandreview.dto.request.AuthenticationRequestDTO;
import it.sportandreview.dto.response.AuthenticationResponseDTO;
import it.sportandreview.enums.Role;
import it.sportandreview.user.User;
import it.sportandreview.user.UserDTO;

public interface AuthenticationService {
    void register(UserDTO userDto, Role role);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
    AuthenticationResponseDTO refreshToken(AuthenticationRequestDTO request);
    AuthenticationResponseDTO generateTokens(User user);
    void updateVerificationStatus(String key, boolean b);
}
