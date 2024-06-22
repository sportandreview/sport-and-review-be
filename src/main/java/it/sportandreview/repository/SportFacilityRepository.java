package it.sportandreview.repository;

import it.sportandreview.entity.SportFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportFacilityRepository extends JpaRepository<SportFacility, Long> {
}
