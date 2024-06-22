package it.sportandreview.service;

import it.sportandreview.dto.request.AuthenticationRequestDTO;
import it.sportandreview.dto.request.UserRequestDTO;
import it.sportandreview.dto.response.AuthenticationResponseDTO;
import it.sportandreview.entity.User;
import it.sportandreview.enums.RoleType;

public interface AuthenticationService {
    void register(UserRequestDTO userRequestDTO, RoleType roleType);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
    AuthenticationResponseDTO refreshToken(AuthenticationRequestDTO request);
    AuthenticationResponseDTO generateTokens(User user);
    void updateVerificationStatus(String key, boolean b);
}
