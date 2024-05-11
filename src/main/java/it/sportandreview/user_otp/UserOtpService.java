package it.sportandreview.user_otp;


import it.sportandreview.user_code_type.UserCodeTypeEnum;

public interface UserOtpService {

    void sendMessage(Long userId);

    void verifyOtp(Long userId, String otp,  UserCodeTypeEnum userCodeType);

    void sendEmail(Long userId);
}
