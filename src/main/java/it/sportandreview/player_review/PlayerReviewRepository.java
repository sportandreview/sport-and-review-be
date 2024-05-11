package it.sportandreview.player_review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerReviewRepository extends JpaRepository<PlayerReview, Long> {
    boolean existsByPlayerUserIdAndMadeById(Long playerUserId, Long madeById);
}


