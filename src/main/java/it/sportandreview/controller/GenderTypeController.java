package it.sportandreview.controller;

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
        private String label;
    }
}
