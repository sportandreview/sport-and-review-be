package it.sportandreview.user_otp;

import it.sportandreview.base.BaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class UserOtpDTO extends BaseDTO {

    private Long userId;
    private String code;
    private Long userCodeTypeId;
    private LocalDateTime expirationDate;
}
