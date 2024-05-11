package it.sportandreview.sport_point;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportPointRepository extends JpaRepository<SportPoint, Long> {


}
