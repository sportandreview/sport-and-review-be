package it.sportandreview.user_otp;

import it.sportandreview.base.IndexedEntity;
import it.sportandreview.user_code_type.UserCodeType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Table(name = "app_user_otp")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserOtp extends IndexedEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code_type_id", referencedColumnName = "id")
    @ToString.Exclude
    private UserCodeType userCodeType;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

}
