package it.sportandreview.review_from_admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewFromAdminRepository extends JpaRepository<ReviewFromAdmin, Long> {
    boolean existsByPlayerUserIdAndAdminUserId(Long playerUserId, Long adminUserId);
}
