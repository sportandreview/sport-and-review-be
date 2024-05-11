package it.sportandreview.auto_evaluation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.gender_type.GenderTypeDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import it.sportandreview.player_user.PlayerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/autoEvaluations")
public class AutoEvaluationController {

    private final AutoEvaluationService service;

    @Autowired
    public AutoEvaluationController(AutoEvaluationService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new auto evaluation")
    public ResponseEntity<Long> create(@Parameter(name = "autoEvaluationDTO") @RequestBody AutoEvaluationDTO autoEvaluationDTO){
        return new ResponseEntity<>(service.create(autoEvaluationDTO), HttpStatus.OK);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create new list of auto evaluations")
    public ResponseEntity<List<AutoEvaluationDTO>> createAll(@Parameter(name = "autoEvaluationDTO") @RequestBody List<AutoEvaluationDTO> autoEvaluationsDTO){
        return new ResponseEntity<>(service.createAll(autoEvaluationsDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update auto evaluation")
    public ResponseEntity<AutoEvaluationDTO> update(@Parameter(name = "autoEvaluationDTO") @RequestBody AutoEvaluationDTO autoEvaluationDTO){
        return new ResponseEntity<>(service.update(autoEvaluationDTO), HttpStatus.OK);
    }

    @Operation(summary = "Find all auto evaluations by player user")
    @GetMapping("/byPlayerUser/{playerUserId}")
    public ResponseEntity<List<AutoEvaluationDTO>> findByPlayerUserId(@PathVariable Long playerUserId) {
        return new ResponseEntity<>(service.findByPlayerUserId(playerUserId), HttpStatus.OK);
    }

}
