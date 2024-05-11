package it.sportandreview.ground_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/groundTypes")
public class GroundTypeController {

    private final GroundTypeService service;

    @Autowired
    public GroundTypeController(GroundTypeService service) {
        this.service = service;
    }



}
