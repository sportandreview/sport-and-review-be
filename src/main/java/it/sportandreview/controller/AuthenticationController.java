package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.sportandreview.dto.request.AuthenticationRequestDTO;
import it.sportandreview.dto.request.UserRequestDTO;
import it.sportandreview.dto.response.AuthenticationResponseDTO;
import it.sportandreview.service.AuthenticationService;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.exception.TokenNotValidException;
import it.sportandreview.exception.UserNotFoundException;
import it.sportandreview.util.OtpUtil;
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
    private final OtpUtil otpUtil;
    private final MessageSource messageSource;

    @PostMapping("/register")
    @Operation(summary = "Registra un nuovo utente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utente registrato con successo"),
            @ApiResponse(responseCode = "400", description = "Richiesta non valida")
    })
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        authenticationService.register(userRequestDTO, userRequestDTO.getRole());

        String emailOtp = otpUtil.generateOtp(userRequestDTO.getEmail());
        otpUtil.sendOtpEmail(userRequestDTO.getEmail(), emailOtp);

        if (userRequestDTO.getMobilePhone() != null && !userRequestDTO.getMobilePhone().isEmpty()) {
            String phoneOtp = otpUtil.generateOtp(userRequestDTO.getMobilePhone());
            otpUtil.sendOtpSms(userRequestDTO.getMobilePhone(), phoneOtp);
        }

        String message = messageSource.getMessage("user.register.success", null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpServletResponse.SC_OK, message));
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Autentica un utente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticazione avvenuta con successo"),
            @ApiResponse(responseCode = "412", description = "Verifica email necessaria"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato"),
            @ApiResponse(responseCode = "401", description = "Credenziali non valide")
    })
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> authenticate(@Valid @RequestBody AuthenticationRequestDTO request) throws UserNotFoundException {
        AuthenticationResponseDTO response = authenticationService.authenticate(request);
        String message = null;
        if (!response.isEmailCheck()) {
            String emailOtp = otpUtil.generateOtp(request.getEmail());
            otpUtil.sendOtpEmail(request.getEmail(), emailOtp);
            message = messageSource.getMessage("user.verify.email", null, LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpServletResponse.SC_PRECONDITION_FAILED)
                    .body(new ApiResponseDTO<>(HttpServletResponse.SC_PRECONDITION_FAILED, message));
        }
        message = messageSource.getMessage("user.authenticate.success", null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpServletResponse.SC_OK, message, response));
    }

    @PutMapping("/refresh")
    @Operation(summary = "Aggiorna il token dell'utente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato"),
            @ApiResponse(responseCode = "401", description = "Token non valido")
    })
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> refreshToken(@Valid @RequestBody AuthenticationRequestDTO request) throws UserNotFoundException, TokenNotValidException {
        ApiResponseDTO<AuthenticationResponseDTO> response = ApiResponseDTO.<AuthenticationResponseDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message(null)
                .result(authenticationService.refreshToken(request))
                .build();
        return ResponseEntity.ok(response);
    }
}
