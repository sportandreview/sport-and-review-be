package it.sportandreview.service_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesTypeRepository extends JpaRepository<ServicesType, Long> {


}
