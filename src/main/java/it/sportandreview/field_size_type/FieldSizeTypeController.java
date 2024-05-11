package it.sportandreview.fieal_size_type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/fieldSizeTypes")
public class FieldSizeTypeController {

    private final FieldSizeTypeService service;

    @Autowired
    public FieldSizeTypeController(FieldSizeTypeService service) {
        this.service = service;
    }


}
