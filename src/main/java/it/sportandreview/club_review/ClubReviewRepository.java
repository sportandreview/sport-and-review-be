package it.sportandreview.club_review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubReviewRepository extends JpaRepository<ClubReview, Long> {
    boolean existsByPlayerUser_IdAndClub_Id(Long playerUserId, Long clubId);
}