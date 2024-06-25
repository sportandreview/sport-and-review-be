package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.sportandreview.dto.request.SportAssessmentListRequestDTO;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.OptionResponseDTO;
import it.sportandreview.dto.response.SportAssessmentWrapperResponseDTO;
import it.sportandreview.enums.SkillLevel;
import it.sportandreview.enums.TrainingFrequency;
import it.sportandreview.service.SportAssessmentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sport-assessments")
public class SportAssessmentController {

    private final SportAssessmentService sportAssessmentService;

    @PostMapping("/user/{userId}")
    @Operation(summary = "Create or update a sport assessment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assessment created/updated successfully"),
            @ApiResponse(responseCode = "404", description = "User or Sport not found")
    })
    public ApiResponseDTO createOrUpdateSportAssessment(
            @PathVariable Long userId,
            @Valid @RequestBody SportAssessmentListRequestDTO requestDTO) {
        sportAssessmentService.createOrUpdateSportAssessmentList(userId, requestDTO);
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, "Autovalutazione salvata con successo!", null);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get sport assessments by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assessments retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "The user doesn't have sport assessment")
    })
    public ApiResponseDTO<SportAssessmentWrapperResponseDTO> getSportAssessmentsByUserId(@PathVariable Long userId) {
        SportAssessmentWrapperResponseDTO response = sportAssessmentService.getSportAssessmentsByUserId(userId);
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, null, response);
    }

    @GetMapping("/options/skill-levels")
    @Operation(summary = "Get skill level options")
    public ApiResponseDTO<List<OptionResponseDTO>> getSkillLevels() {
        List<OptionResponseDTO> options = Arrays.stream(SkillLevel.values())
                .map(level -> new OptionResponseDTO(level.name(), level.getDescription()))
                .collect(Collectors.toList());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, null, options);
    }

    @GetMapping("/options/training-frequencies")
    @Operation(summary = "Get training frequency options")
    public ApiResponseDTO<List<OptionResponseDTO>> getTrainingFrequencies() {
        List<OptionResponseDTO> options = Arrays.stream(TrainingFrequency.values())
                .map(frequency -> new OptionResponseDTO(frequency.name(), frequency.getDescription()))
                .collect(Collectors.toList());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, null, options);
    }
}
