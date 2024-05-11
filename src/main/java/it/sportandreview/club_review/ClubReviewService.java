package it.sportandreview.club_review;


import java.util.List;

public interface ClubReviewService {

    Long create(ClubReviewDTO clubReviewDTO);

    ClubReviewDTO update(ClubReviewDTO clubReviewDTO);

    List<ClubReviewDTO> findAll();

    ClubReviewDTO findById(Long clubReviewId);
}
