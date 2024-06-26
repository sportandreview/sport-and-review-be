package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.sportandreview.dto.request.AuthenticationRequestDTO;
import it.sportandreview.dto.request.UserRequestDTO;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.AuthenticationResponseDTO;
import it.sportandreview.service.AuthenticationService;
import it.sportandreview.util.OtpUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
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
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<ApiResponseDTO<Void>> register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        authenticationService.register(userRequestDTO, userRequestDTO.getRole());

        String emailOtp = otpUtil.generateOtp(userRequestDTO.getEmail());
        otpUtil.sendOtpEmail(userRequestDTO.getEmail(), emailOtp);

        if (userRequestDTO.getMobilePhone() != null && !userRequestDTO.getMobilePhone().isEmpty()) {
            String phoneOtp = otpUtil.generateOtp(userRequestDTO.getMobilePhone());
            otpUtil.sendOtpSms(userRequestDTO.getMobilePhone(), phoneOtp);
        }

        String message = messageSource.getMessage("user.register.success", null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDTO<>(HttpStatus.CREATED.value(), message));
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "412", description = "Email verification required"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> authenticate(@Valid @RequestBody AuthenticationRequestDTO request) {
        AuthenticationResponseDTO response = authenticationService.authenticate(request);
        if (!response.isEmailCheck()) {
            String emailOtp = otpUtil.generateOtp(request.getEmail());
            otpUtil.sendOtpEmail(request.getEmail(), emailOtp);
            String message = messageSource.getMessage("user.verify.email", null, LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                    .body(new ApiResponseDTO<>(HttpStatus.PRECONDITION_FAILED.value(), message));
        }
        String message = messageSource.getMessage("user.authenticate.success", null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK.value(), message, response));
    }

    @PutMapping("/refresh")
    @Operation(summary = "Refresh user token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "401", description = "Invalid token")
    })
    public ResponseEntity<ApiResponseDTO<AuthenticationResponseDTO>> refreshToken(@Valid @RequestBody AuthenticationRequestDTO request) {
        AuthenticationResponseDTO refreshedToken = authenticationService.refreshToken(request);
        String message = messageSource.getMessage("token.refresh.success", null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK.value(), message, refreshedToken));
    }
}