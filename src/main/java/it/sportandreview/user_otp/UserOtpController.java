package it.sportandreview.user_otp;

import io.swagger.v3.oas.annotations.Operation;
import it.sportandreview.dto.ApiResponseDTO;
import it.sportandreview.user_code_type.UserCodeTypeEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class UserOtpController {

    private final UserOtpService service;
    private final ResetPassword resetPassword;

    @Autowired
    public UserOtpController(UserOtpService service, ResetPassword resetPassword) {

        this.service = service;
        this.resetPassword = resetPassword;
    }

    @PostMapping("/sendMessage/{userId}")
    @Operation(summary = "generate otp on mobile phone")
    public ResponseEntity<ApiResponseDTO<Void>> sendMessage(@PathVariable Long userId) {
        service.sendMessage(userId);
        ApiResponseDTO<Void> response = ApiResponseDTO.<Void>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Messaggio inviato con successo")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/mobilePhone/{userId}/{otp}")
    @Operation(summary = "verify mobile phone")
    public ResponseEntity<ApiResponseDTO<Void>> verifyMobilePhone(@PathVariable Long userId, @PathVariable String otp) {
        service.verifyOtp(userId, otp, UserCodeTypeEnum.PHONE);
        ApiResponseDTO<Void> response = ApiResponseDTO.<Void>builder()
                .status(HttpServletResponse.SC_OK)
                .message("verifica OTP effettuata con successo")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sendEmail/{userId}")
    @Operation(summary = "verify email")
    public ResponseEntity<ApiResponseDTO<Void>> sendEmail(@PathVariable Long userId) {
        service.sendEmail(userId);
        ApiResponseDTO<Void> response = ApiResponseDTO.<Void>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Email inviata con successo")
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/email/{userId}/{otp}")
    @Operation(summary = "verify email")
    public ResponseEntity<ApiResponseDTO<Void>> verifyEmail(@PathVariable Long userId, @PathVariable String otp) {
        service.verifyOtp(userId, otp, UserCodeTypeEnum.EMAIL);
        ApiResponseDTO<Void> response = ApiResponseDTO.<Void>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Email verificata con successo")
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/sendEmailChangePassword/{userId}")
    @Operation(summary = "change password")
    public ResponseEntity<ApiResponseDTO<String>> sendEmailChangePassword(@PathVariable Long userId) {
        ApiResponseDTO<String> response = ApiResponseDTO.<String>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Email inviata con successo")
                .result(resetPassword.sendEmailChangePassword(userId))
                .build();
        return ResponseEntity.ok(response);
    }

}
