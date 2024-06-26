package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.service.AuthenticationService;
import it.sportandreview.util.OtpUtil;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/otp")
@Validated
public class OtpController {

    private final OtpUtil otpUtil;
    private final AuthenticationService authenticationService;
    private final MessageSource messageSource;

    @PostMapping("/send")
    @Operation(summary = "Send OTP to email or phone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OTP sent successfully")
    })
    public ResponseEntity<ApiResponseDTO<Void>> sendOtp(@RequestParam @NotBlank String key) {
        String otp = otpUtil.generateOtp(key);
        if (key.contains("@")) {
            otpUtil.sendOtpEmail(key, otp);
        } else {
            otpUtil.sendOtpSms(key, otp);
        }
        String message = messageSource.getMessage("otp.sent", null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK.value(), message));
    }

    @PostMapping("/verify")
    @Operation(summary = "Verify OTP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OTP verified successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid OTP")
    })
    public ResponseEntity<ApiResponseDTO<Void>> verifyOtp(
            @RequestParam @NotBlank String key,
            @RequestParam @NotBlank String otp) {
        boolean isValid = otpUtil.validateOtp(key, otp);
        if (isValid) {
            authenticationService.updateVerificationStatus(key, true);
            String message = messageSource.getMessage("otp.verified", null, LocaleContextHolder.getLocale());
            return ResponseEntity.ok(new ApiResponseDTO<>(HttpStatus.OK.value(), message));
        } else {
            String message = messageSource.getMessage("otp.invalid", null, LocaleContextHolder.getLocale());
            return ResponseEntity.badRequest().body(new ApiResponseDTO<>(HttpStatus.BAD_REQUEST.value(), message));
        }
    }
}