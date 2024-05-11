package it.sportandreview.sport_equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SportEquipmentRepository extends JpaRepository<SportEquipment, Long> {

}
