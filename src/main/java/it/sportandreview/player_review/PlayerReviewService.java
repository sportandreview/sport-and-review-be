package it.sportandreview.player_review;


import java.util.List;

public interface PlayerReviewService {

    Long create(PlayerReviewDTO playerReviewDTO);
    List<PlayerReviewDTO> findAll();
    PlayerReviewDTO findById(Long playerReviewId);

}
