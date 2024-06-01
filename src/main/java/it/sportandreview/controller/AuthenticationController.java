package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import it.sportandreview.admin_user.AdminUserDTO;
import it.sportandreview.dto.request.AuthenticationRequestDTO;
import it.sportandreview.dto.response.AuthenticationResponseDTO;
import it.sportandreview.service.AuthenticationService;
import it.sportandreview.dto.response.ApiResponseDTO;
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
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> register(@Valid @RequestBody PlayerUserDTO playerUserDTO) throws UserAlreadyExistException {
        authenticationService.register(playerUserDTO, Role.USER);
        ApiResponseDTO<AuthenticationResponseDTO> response = ApiResponseDTO.<AuthenticationResponseDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Player registrato con successo!")
                .build();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/register/admin")
    @Operation(summary = "Registra un nuovo utente admin")
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> register(@Valid @RequestBody AdminUserDTO adminUserDTO) throws UserAlreadyExistException {
        authenticationService.register(adminUserDTO, Role.ADMIN);
        ApiResponseDTO<AuthenticationResponseDTO> response = ApiResponseDTO.<AuthenticationResponseDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Admin registrato con successo")
                .result(null)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Autentica un utente")
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> authenticate(@Valid @RequestBody AuthenticationRequestDTO request) throws UserNotFoundException {
        ApiResponseDTO<AuthenticationResponseDTO> response = ApiResponseDTO.<AuthenticationResponseDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Utente autenticato con successo")
                .result(authenticationService.authenticate(request))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/refresh")
    @Operation(summary = "Aggiorna il token dell'utente")
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> refreshToken(@Valid @RequestBody AuthenticationRequestDTO request) throws UserNotFoundException, TokenNotValidException {
        ApiResponseDTO<AuthenticationResponseDTO> response = ApiResponseDTO.<AuthenticationResponseDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Token aggiornato con successo")
                .result(authenticationService.refreshToken(request))
                .build();
        return ResponseEntity.ok(response);
    }
}
