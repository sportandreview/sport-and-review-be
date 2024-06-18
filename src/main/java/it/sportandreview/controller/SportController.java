package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.SportResponseDTO;
import it.sportandreview.entity.Sport;
import it.sportandreview.mapper.SportMapper;
import it.sportandreview.service.SportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/sports")
public class SportController {

    private final SportService sportService;
    private final SportMapper sportMapper;
    private final MessageSource messageSource;

    @Operation(summary = "Get all sports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sports retrieved successfully")
    })
    @GetMapping
    public ApiResponseDTO<List<SportResponseDTO>> getAllSports() {
        List<SportResponseDTO> sports = sportService.getAllSports().stream()
                .map(sportMapper::toDto)
                .collect(Collectors.toList());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, null, sports);
    }

    @Operation(summary = "Get sport by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sport retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Sport not found")
    })
    @GetMapping("/{id}")
    public ApiResponseDTO<SportResponseDTO> getSportById(@PathVariable Long id) {
        Sport sport = sportService.getSportById(id);
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, null, sportMapper.toDto(sport));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create a new sport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sport created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public ApiResponseDTO<SportResponseDTO> createSport(@RequestBody Sport sport) {
        Sport createdSport = sportService.createSport(sport);
        String message = messageSource.getMessage("sport.create.success", null, LocaleContextHolder.getLocale());
        return new ApiResponseDTO<>(HttpServletResponse.SC_CREATED, message, sportMapper.toDto(createdSport));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update an existing sport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sport updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Sport not found")
    })
    @PutMapping("/{id}")
    public ApiResponseDTO<SportResponseDTO> updateSport(@PathVariable Long id, @RequestBody Sport sportDetails) {
        Sport updatedSport = sportService.updateSport(id, sportDetails);
        String message = messageSource.getMessage("sport.update.success", null, LocaleContextHolder.getLocale());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, message, sportMapper.toDto(updatedSport));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a sport by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sport deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Sport not found")
    })
    @DeleteMapping("/{id}")
    public ApiResponseDTO<Void> deleteSport(@PathVariable Long id) {
        sportService.deleteSport(id);
        String message = messageSource.getMessage("sport.delete.success", null, LocaleContextHolder.getLocale());
        return new ApiResponseDTO<>(HttpServletResponse.SC_NO_CONTENT, message, null);
    }
}
