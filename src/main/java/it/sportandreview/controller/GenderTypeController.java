package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.enums.GenderType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gender-types")
public class GenderTypeController {

    @Operation(summary = "Get all gender types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gender types retrieved successfully")
    })
    @GetMapping
    public ApiResponseDTO<List<GenderTypeResponse>> getGenderTypes() {
        List<GenderTypeResponse> genderTypes = Arrays.stream(GenderType.values())
                .map(genderType -> new GenderTypeResponse(genderType.name(), genderType.getDescription()))
                .collect(Collectors.toList());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, null, genderTypes);
    }

    @Data
    @AllArgsConstructor
    static class GenderTypeResponse {
        private String value;
        private String description;
    }
}
