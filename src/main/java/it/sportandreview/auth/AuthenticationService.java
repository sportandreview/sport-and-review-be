package it.sportandreview.auth;

import it.sportandreview.user.Role;
import it.sportandreview.user.User;
import it.sportandreview.user.UserDTO;

public interface AuthenticationService {
    void register(UserDTO userDto, Role role);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse refreshToken(AuthenticationRequest request);
    AuthenticationResponse generateTokens(User user);
}
