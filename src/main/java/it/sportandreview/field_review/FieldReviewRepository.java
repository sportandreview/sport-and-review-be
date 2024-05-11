package it.sportandreview.field_review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldReviewRepository extends JpaRepository<FieldReview, Long> {

}
