package it.sportandreview.review_from_host;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewFromHostRepository extends JpaRepository<ReviewFromHost, Long> {

}
