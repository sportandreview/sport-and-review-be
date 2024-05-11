package it.sportandreview.user_otp;

import io.swagger.v3.oas.annotations.Operation;
import it.sportandreview.user_code_type.UserCodeTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Void> sendMessage(@PathVariable Long userId) {
        service.sendMessage(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/mobilePhone/{userId}/{otp}")
    @Operation(summary = "verify mobile phone")
    public ResponseEntity<Void> verifyMobilePhone(@PathVariable Long userId, @PathVariable String otp) {
        service.verifyOtp(userId, otp, UserCodeTypeEnum.PHONE);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sendEmail/{userId}")
    @Operation(summary = "verify email")
    public ResponseEntity<Void> sendEmail(@PathVariable Long userId) {
        service.sendEmail(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/email/{userId}/{otp}")
    @Operation(summary = "verify email")
    public ResponseEntity<Void> verifyEmail(@PathVariable Long userId, @PathVariable String otp) {
        service.verifyOtp(userId, otp, UserCodeTypeEnum.EMAIL);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/sendEmailChangePassword/{userId}")
    @Operation(summary = "change password")
    public ResponseEntity<String> sendEmailChangePassword(@PathVariable Long userId) {
        resetPassword.sendEmailChangePassword(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
