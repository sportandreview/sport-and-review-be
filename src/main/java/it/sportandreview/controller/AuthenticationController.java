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
import it.sportandreview.service.OtpService;
import it.sportandreview.enums.Role;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final OtpService otpService;
    private final MessageSource messageSource;

    @PostMapping("/register/player")
    @Operation(summary = "Registra un nuovo utente player")
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> registerPlayer(@Valid @RequestBody PlayerUserDTO playerUserDTO) {
        authenticationService.register(playerUserDTO, Role.USER);
        String emailOtp = otpService.generateOtp(playerUserDTO.getEmail());
        otpService.sendOtpEmail(playerUserDTO.getEmail(), emailOtp);
        if (playerUserDTO.getMobilePhone() != null && !playerUserDTO.getMobilePhone().isEmpty()) {
            String phoneOtp = otpService.generateOtp(playerUserDTO.getMobilePhone());
            otpService.sendOtpSms(playerUserDTO.getMobilePhone(), phoneOtp);
        }
        String message = messageSource.getMessage("user.register.success", null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpServletResponse.SC_OK, message));
    }

    @PostMapping("/register/admin")
    @Operation(summary = "Registra un nuovo utente admin")
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> registerAdmin(@Valid @RequestBody AdminUserDTO adminUserDTO) throws UserAlreadyExistException {
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
        AuthenticationResponseDTO response = authenticationService.authenticate(request);
        if (!response.isEmailCheck()) {
            String emailOtp = otpService.generateOtp(request.getEmail());
            otpService.sendOtpEmail(request.getEmail(), emailOtp);
            String message = messageSource.getMessage("user.verify.email", null, LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED)
                    .body(new ApiResponseDTO<>(HttpServletResponse.SC_UNAUTHORIZED, message));
        }
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpServletResponse.SC_OK, "Utente autenticato con successo", response));
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
