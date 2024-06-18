package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.OptionResponseDTO;
import it.sportandreview.dto.request.UserEvaluationRequestDTO;
import it.sportandreview.dto.response.UserEvaluationResponseDTO;
import it.sportandreview.enums.PhysicalStructure;
import it.sportandreview.enums.SkillLevel;
import it.sportandreview.enums.TrainingFrequency;
import it.sportandreview.service.UserEvaluationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/evaluations")
public class UserEvaluationController {

    private final UserEvaluationService userEvaluationService;

    @PostMapping("/{userId}")
    @Operation(summary = "Evaluate user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User evaluated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ApiResponseDTO<UserEvaluationResponseDTO> evaluateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserEvaluationRequestDTO requestDTO) {
        UserEvaluationResponseDTO response = userEvaluationService.evaluateUser(userId, requestDTO);
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, "Evaluation saved successfully", response);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user evaluations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User evaluations retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ApiResponseDTO<List<UserEvaluationResponseDTO>> getUserEvaluations(@PathVariable Long userId) {
        List<UserEvaluationResponseDTO> response = userEvaluationService.getUserEvaluations(userId);
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, "Evaluations retrieved successfully", response);
    }

    @GetMapping("/options/physical-structures")
    @Operation(summary = "Get physical structure options")
    public ApiResponseDTO<List<OptionResponseDTO>> getPhysicalStructures() {
        List<OptionResponseDTO> options = Arrays.stream(PhysicalStructure.values())
                .map(structure -> new OptionResponseDTO(structure.name(), structure.getDescription()))
                .collect(Collectors.toList());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, "Success", options);
    }

    @GetMapping("/options/skill-levels")
    @Operation(summary = "Get skill level options")
    public ApiResponseDTO<List<OptionResponseDTO>> getSkillLevels() {
        List<OptionResponseDTO> options = Arrays.stream(SkillLevel.values())
                .map(level -> new OptionResponseDTO(level.name(), level.getDescription()))
                .collect(Collectors.toList());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, "Success", options);
    }

    @GetMapping("/options/training-frequencies")
    @Operation(summary = "Get training frequency options")
    public ApiResponseDTO<List<OptionResponseDTO>> getTrainingFrequencies() {
        List<OptionResponseDTO> options = Arrays.stream(TrainingFrequency.values())
                .map(frequency -> new OptionResponseDTO(frequency.name(), frequency.getDescription()))
                .collect(Collectors.toList());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, "Success", options);
    }
}
