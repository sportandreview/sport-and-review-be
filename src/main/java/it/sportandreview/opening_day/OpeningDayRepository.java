package it.sportandreview.opening_day;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningDayRepository extends JpaRepository<OpeningDay, Long> {

}
