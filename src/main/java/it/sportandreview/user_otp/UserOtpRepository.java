package it.sportandreview.user_otp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {
    @Query("SELECT o FROM UserOtp o WHERE o.userId = :userId AND o.userCodeType.id = :userCodeType" +
            " AND o.expirationDate = (SELECT MAX(o2.expirationDate) FROM UserOtp o2 WHERE o2.userId = :userId AND " +
            "o2.userCodeType.id = :userCodeType)")
    UserOtp findLatestCodeByUserIdAndType(@Param("userId") Long userId, Long userCodeType);

   Optional<UserOtp> findByCode(String code);

    void deleteAllByUserIdAndUserCodeTypeId(@Param("userId") Long userId, @Param("userCodeTypeId") Long userCodeTypeId);

}
