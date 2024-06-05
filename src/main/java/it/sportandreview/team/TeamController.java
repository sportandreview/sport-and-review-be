package it.sportandreview.team;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.ApiResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService service;

    @Autowired
    public TeamController(TeamService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new team")
    public ResponseEntity<ApiResponseDTO<Long>> create(@Parameter(name = "teamDTO") @Valid @RequestBody TeamDTO teamDTO){
        ApiResponseDTO<Long> response = ApiResponseDTO.<Long>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Team creato con successo")
                .result(service.create(teamDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Operation(summary = "Update team")
    public ResponseEntity<ApiResponseDTO<TeamDTO>> update(@Parameter(name = "clubDTO") @Valid @RequestBody TeamDTO teamDTO){
        ApiResponseDTO<TeamDTO> response = ApiResponseDTO.<TeamDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Team aggiornato con successo")
                .result(service.update(teamDTO))
                .build();
        return ResponseEntity.ok(response);
    }
}
