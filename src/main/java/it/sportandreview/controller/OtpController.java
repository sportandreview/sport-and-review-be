package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.service.OtpService;
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

    private final OtpService otpService;
    private final AuthenticationService authenticationService;
    private final MessageSource messageSource;

    @PostMapping("/send")
    @Operation(summary = "Invia OTP a email o cellulare")
    public ResponseEntity<ApiResponseDTO<String>> sendOtp(@RequestParam String key) {
        String otp = otpService.generateOtp(key);
        if (key.contains("@")) {
            otpService.sendOtpEmail(key, otp);
        } else {
            otpService.sendOtpSms(key, otp);
        }
        String message = messageSource.getMessage("otp.sent", null, LocaleContextHolder.getLocale());
        return ResponseEntity.ok(new ApiResponseDTO<>(HttpServletResponse.SC_OK, message));
    }

    @PostMapping("/verify")
    @Operation(summary = "Verifica OTP")
    public ResponseEntity<ApiResponseDTO<String>> verifyOtp(@RequestParam String key, @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(key, otp);
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
