package it.sportandreview.highlight;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.gender_type.GenderTypeDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/highlights")
public class HighlightController {

    private final HighlightService service;

    @Autowired
    public HighlightController(HighlightService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new highlight")
    public ResponseEntity<Long> create(@Parameter(name = "highlightDto") @RequestBody HighlightDTO highlightDTO){
        return new ResponseEntity<>(service.create(highlightDTO), HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update highlight")
    public ResponseEntity<HighlightDTO> update(@Parameter(name = "highlightDto") @RequestBody HighlightDTO highlightDTO){
        return new ResponseEntity<>(service.update(highlightDTO), HttpStatus.OK);
    }
}
