package it.sportandreview.auth;

import it.sportandreview.configuration.JwtService;
import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.user.Role;
import it.sportandreview.user.User;
import it.sportandreview.user.UserDTO;
import it.sportandreview.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 60;
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 70;

    public AuthenticationResponse register(UserDTO userDto, Role role) {
       Optional<User> opt = userService.findByEmail(userDto.getEmail());
       if (opt.isEmpty()) {
           userDto.setRole(role);
           User user = userService.create(userDto);
           return generateTokens(user);
       } else {
           throw new CreateEntityException("player user", " L'EMAIL INSERITA E' GIA' IN USO. ");
       }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userService.findByEmail(request.getEmail()).orElseThrow();
        return generateTokens(user);
    }

    public AuthenticationResponse refreshToken(AuthenticationRequest request) {
        User user = userService.findByEmail(request.getEmail()).orElseThrow();
        if (jwtService.isTokenValid(request.getRefreshToken(), user)) {
            return generateTokens(user);
        }
        return null;
    }

    private AuthenticationResponse generateTokens(User user) {
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
