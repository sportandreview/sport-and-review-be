package it.sportandreview.review_from_admin;




import java.util.List;

public interface ReviewFromAdminService {

    Long create(ReviewFromAdminDTO reviewFromAdminDTO);
    List<ReviewFromAdminDTO> findAll();
    ReviewFromAdminDTO findById(Long playerReviewId);
}
