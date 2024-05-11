package it.sportandreview.sport_equipment_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SportEquipmentTypeRepository extends JpaRepository<SportEquipmentType, Long> {

}
