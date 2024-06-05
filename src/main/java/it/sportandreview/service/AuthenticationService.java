package it.sportandreview.service;

import it.sportandreview.exception.BadCredentialsException;
import it.sportandreview.exception.TokenNotValidException;
import it.sportandreview.exception.UserAlreadyExistException;
import it.sportandreview.exception.UserNotFoundException;
import it.sportandreview.dto.request.AuthenticationRequestDTO;
import it.sportandreview.dto.response.AuthenticationResponseDTO;
import it.sportandreview.user.Role;
import it.sportandreview.user.User;
import it.sportandreview.user.UserDTO;

public interface AuthenticationService {
    void register(UserDTO userDto, Role role) throws UserAlreadyExistException;
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) throws UserNotFoundException, BadCredentialsException;
    AuthenticationResponseDTO refreshToken(AuthenticationRequestDTO request) throws UserNotFoundException, TokenNotValidException;
    AuthenticationResponseDTO generateTokens(User user);
}
