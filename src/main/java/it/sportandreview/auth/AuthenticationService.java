package it.sportandreview.auth;

import it.sportandreview.exception.TokenNotValidException;
import it.sportandreview.exception.UserAlreadyExistException;
import it.sportandreview.exception.UserNotFoundException;
import it.sportandreview.user.Role;
import it.sportandreview.user.User;
import it.sportandreview.user.UserDTO;

public interface AuthenticationService {
    void register(UserDTO userDto, Role role) throws UserAlreadyExistException;
    AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException;
    AuthenticationResponse refreshToken(AuthenticationRequest request) throws UserNotFoundException, TokenNotValidException;
    AuthenticationResponse generateTokens(User user);
}
