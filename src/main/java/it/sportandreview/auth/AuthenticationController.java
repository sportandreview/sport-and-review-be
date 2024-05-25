package it.sportandreview.auth;

import io.swagger.v3.oas.annotations.Operation;
import it.sportandreview.admin_user.AdminUserDTO;
import it.sportandreview.dto.ApiResponseDTO;
import it.sportandreview.exception.TokenNotValidException;
import it.sportandreview.exception.UserAlreadyExistException;
import it.sportandreview.exception.UserNotFoundException;
import it.sportandreview.player_user.PlayerUserDTO;
import it.sportandreview.user.Role;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register/player")
    @Operation(summary = "Registra un nuovo utente player")
    public ResponseEntity<ApiResponseDTO<AuthenticationResponse>> register(@Valid @RequestBody PlayerUserDTO playerUserDTO) throws UserAlreadyExistException {
        authenticationService.register(playerUserDTO, Role.USER);
        ApiResponseDTO<AuthenticationResponse> response = ApiResponseDTO.<AuthenticationResponse>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Player registrato con successo!")
                .build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/register/admin")
    @Operation(summary = "Registra un nuovo utente admin")
    public ResponseEntity<ApiResponseDTO<AuthenticationResponse>> register(@Valid @RequestBody AdminUserDTO adminUserDTO) throws UserAlreadyExistException {
        authenticationService.register(adminUserDTO, Role.ADMIN);
        ApiResponseDTO<AuthenticationResponse> response = ApiResponseDTO.<AuthenticationResponse>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Admin registrato con successo")
                .result(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Autentica un utente")
    public ResponseEntity<ApiResponseDTO<AuthenticationResponse>> authenticate(@Valid @RequestBody AuthenticationRequest request) throws UserNotFoundException {
        ApiResponseDTO<AuthenticationResponse> response = ApiResponseDTO.<AuthenticationResponse>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Utente autenticato con successo")
                .result(authenticationService.authenticate(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/refresh")
    @Operation(summary = "Aggiorna il token dell'utente")
    public ResponseEntity<ApiResponseDTO<AuthenticationResponse>> refreshToken(@Valid @RequestBody AuthenticationRequest request) throws UserNotFoundException, TokenNotValidException {
        ApiResponseDTO<AuthenticationResponse> response = ApiResponseDTO.<AuthenticationResponse>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Token aggiornato con successo")
                .result(authenticationService.refreshToken(request))
                .build();
        return ResponseEntity.ok(response);
    }
}
