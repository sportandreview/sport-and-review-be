package it.sportandreview.auth;

import io.swagger.v3.oas.annotations.Operation;
import it.sportandreview.admin_user.AdminUserDTO;
import it.sportandreview.dto.GenericResponseDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import it.sportandreview.user.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    public ResponseEntity<GenericResponseDTO<AuthenticationResponse>> register(@Valid @RequestBody PlayerUserDTO playerUserDTO) {
        authenticationService.register(playerUserDTO, Role.USER);
        GenericResponseDTO<AuthenticationResponse> response = GenericResponseDTO.<AuthenticationResponse>builder()
                .message("Player registrato con successo")
                .data(null)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/register/admin")
    @Operation(summary = "Registra un nuovo utente admin")
    public ResponseEntity<GenericResponseDTO<AuthenticationResponse>> register(@Valid @RequestBody AdminUserDTO adminUserDTO) {
        authenticationService.register(adminUserDTO, Role.ADMIN);
        GenericResponseDTO<AuthenticationResponse> response = GenericResponseDTO.<AuthenticationResponse>builder()
                .message("Admin registrato con successo")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Autentica un utente")
    public ResponseEntity<GenericResponseDTO<AuthenticationResponse>> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        GenericResponseDTO<AuthenticationResponse> response = GenericResponseDTO.<AuthenticationResponse>builder()
                .message("Utente autenticato con successo")
                .data(authenticationService.authenticate(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/refresh")
    @Operation(summary = "Aggiorna il token dell'utente")
    public ResponseEntity<GenericResponseDTO<AuthenticationResponse>> refreshToken(@Valid @RequestBody AuthenticationRequest request) {
        GenericResponseDTO<AuthenticationResponse> response = GenericResponseDTO.<AuthenticationResponse>builder()
                .message("Token aggiornato con successo")
                .data(authenticationService.refreshToken(request))
                .build();
        return ResponseEntity.ok(response);
    }
}
