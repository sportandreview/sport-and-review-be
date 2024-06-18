package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.util.OtpUtil;
import it.sportandreview.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/otp")
public class OtpController {

    private final OtpUtil otpUtil;
    private final AuthenticationService authenticationService;
    private final MessageSource messageSource;

    @Operation(summary = "Send OTP to email or phone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OTP sent successfully")
    })
    @PostMapping("/send")
    public ResponseEntity<ApiResponseDTO<String>> sendOtp(@RequestParam String key) {
        String otp = otpUtil.generateOtp(key);
        if (key.contains("@")) {
            otpUtil.sendOtpEmail(key, otp);
        } else {
            otpUtil.sendOtpSms(key, otp);
        }
        String message = messageSource.getMessage("otp.sent", null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpServletResponse.SC_OK, message));
    }

    @Operation(summary = "Verify OTP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OTP verified successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid OTP")
    })
    @PostMapping("/verify")
    public ResponseEntity<ApiResponseDTO<String>> verifyOtp(@RequestParam String key, @RequestParam String otp) {
        boolean isValid = otpUtil.validateOtp(key, otp);
        if (isValid) {
            authenticationService.updateVerificationStatus(key, true);
            String message = messageSource.getMessage("otp.verified", null, LocaleContextHolder.getLocale());
            return ResponseEntity.ok(new ApiResponseDTO<>(HttpServletResponse.SC_OK, message));
        } else {
            String message = messageSource.getMessage("otp.invalid", null, LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(new ApiResponseDTO<>(HttpServletResponse.SC_BAD_REQUEST, message));
        }
    }
}
