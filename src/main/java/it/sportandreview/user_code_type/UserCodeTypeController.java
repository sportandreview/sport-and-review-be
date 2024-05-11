package it.sportandreview.user_code_type;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.club.ClubDTO;
import it.sportandreview.highlight.HighlightDTO;
import it.sportandreview.sport.SportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/userCodeTypes")
public class UserCodeTypeController {

    private final UserCodeTypeService service;

    @Autowired
    public UserCodeTypeController(UserCodeTypeService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new user code type")
    public ResponseEntity<Long> create(@Parameter(name = "userCodeTypeDTO") @RequestBody UserCodeTypeDTO userCodeTypeDTO){
        return new ResponseEntity<>(service.create(userCodeTypeDTO), HttpStatus.OK);
    }

    @GetMapping("/userCodeTypes")
    public ResponseEntity<List<UserCodeTypeDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("{userCodeTypeId}")
    @Operation(summary = "Find user code type by id")
    public ResponseEntity<UserCodeTypeDTO> findById(@PathVariable Long userCodeTypeId) {
        return new ResponseEntity<>(service.findById(userCodeTypeId), HttpStatus.OK);
    }
}
