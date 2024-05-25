package it.sportandreview.auth;

import it.sportandreview.configuration.JwtService;
import it.sportandreview.exception.TokenNotValidException;
import it.sportandreview.exception.UserAlreadyExistException;
import it.sportandreview.exception.UserNotFoundException;
import it.sportandreview.user.Role;
import it.sportandreview.user.User;
import it.sportandreview.user.UserDTO;
import it.sportandreview.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 60;
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 70;

    @Override
    public void register(UserDTO userDto, Role role) throws UserAlreadyExistException {
        log.info("register Player START");
        if(userService.findByEmail(userDto.getEmail()).isPresent())  throw new UserAlreadyExistException("Email già in uso!");
        userDto.setRole(role);
        userService.create(userDto);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userService.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException());
        return generateTokens(user);
    }

    @Override
    public AuthenticationResponse refreshToken(AuthenticationRequest request) throws UserNotFoundException, TokenNotValidException {
        User user = userService.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException());
        if (jwtService.isTokenValid(request.getRefreshToken(), user)) {
            return generateTokens(user);
        }
        throw new TokenNotValidException("Token di refresh non valido");
    }

    public AuthenticationResponse generateTokens(User user) {
        String jwtToken = jwtService.generateToken(user, TOKEN_EXPIRATION_TIME);
        String refreshToken = jwtService.generateToken(user, REFRESH_TOKEN_EXPIRATION_TIME);
        return AuthenticationResponse.builder()
                .userId(user.getId())
                .token(jwtToken)
                .refreshToken(refreshToken)
                .mobilePhoneCheck(user.isMobilePhoneCheck())
                .emailCheck(user.isEmailCheck())
                .build();
    }
}
