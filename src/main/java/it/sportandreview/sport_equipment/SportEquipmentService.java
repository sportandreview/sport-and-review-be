package it.sportandreview.sport_equipment;


import java.util.List;

public interface SportEquipmentService {

    Long create(SportEquipmentDTO sportEquipmentDto);

    SportEquipmentDTO update(SportEquipmentDTO sportEquipmentDto);

    List<SportEquipmentDTO> findAll();

    SportEquipmentDTO findById(Long sportEquipmentId);

}
