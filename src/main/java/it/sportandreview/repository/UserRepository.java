package it.sportandreview.repository;

import it.sportandreview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmailOrMobilePhone(String email, String mobilePhone);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.sportAssessmentSet sa " +
            "LEFT JOIN FETCH sa.sport s " +
            "WHERE u.id = :userId")
    Optional<User> findByIdWithSportAssessments(@Param("userId") Long userId);
}
