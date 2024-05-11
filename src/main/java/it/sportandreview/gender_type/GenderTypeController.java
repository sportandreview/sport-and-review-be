package it.sportandreview.gender_type;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genderTypes")
public class GenderTypeController {

    private final GenderTypeService service;

    @Autowired
    public GenderTypeController(GenderTypeService service) {
        this.service = service;
    }

}
