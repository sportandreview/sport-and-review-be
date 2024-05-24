package it.sportandreview.configuration;

import it.sportandreview.auth.AuthenticationResponse;
import it.sportandreview.auth.AuthenticationService;
import it.sportandreview.user.Role;
import it.sportandreview.user.User;
import it.sportandreview.user.UserDTO;
import it.sportandreview.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GoogleOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Lazy
    private final UserService userService;
    @Lazy
    private final AuthenticationService authenticationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        String email = oidcUser.getEmail();
        User user = userService.findByEmail(email).orElseGet(() -> {
            UserDTO newUser = new UserDTO();
            newUser.setEmail(email);
            newUser.setRole(Role.USER);
            newUser.setName(oidcUser.getGivenName());
            newUser.setSurname(oidcUser.getFamilyName());
            return userService.create(newUser);
        });

        AuthenticationResponse authResponse = authenticationService.generateTokens(user);

        String redirectUrl = UriComponentsBuilder.fromUriString("/success")
                .queryParam("token", authResponse.getToken())
                .queryParam("refreshToken", authResponse.getRefreshToken())
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
