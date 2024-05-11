package it.sportandreview.gender_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderTypeRepository extends JpaRepository<GenderType, Long> {

    GenderType findByDescription(String description);
}
