package it.sportandreview.repository;

import it.sportandreview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmailOrMobilePhone(String email, String mobilePhone);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}
