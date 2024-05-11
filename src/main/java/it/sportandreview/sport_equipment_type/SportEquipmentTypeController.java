package it.sportandreview.sport_equipment_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/sportEquipmentType")
public class SportEquipmentTypeController {

    private final SportEquipmentTypeService service;

    @Autowired
    public SportEquipmentTypeController(SportEquipmentTypeService service) {
        this.service = service;
    }
}
