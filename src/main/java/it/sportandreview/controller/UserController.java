package it.sportandreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.dto.response.OptionResponseDTO;
import it.sportandreview.dto.response.UserResponseDTO;
import it.sportandreview.entity.Sport;
import it.sportandreview.entity.User;
import it.sportandreview.enums.PhysicalStructure;
import it.sportandreview.mapper.UserMapper;
import it.sportandreview.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final MessageSource messageSource;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "View a list of all users", description = "Retrieve a list of all users", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
    @GetMapping
    public ApiResponseDTO<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, null, users);
    }

    @Operation(summary = "Get a user by Id", description = "Retrieve a user by their ID", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
    @GetMapping("/{id}")
    public ApiResponseDTO<UserResponseDTO> getUserById(
            @PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, null, userMapper.toDto(user));
    }

    @Operation(summary = "Update an existing user", description = "Update an existing user", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
    @PutMapping("/{id}")
    public ApiResponseDTO<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        String message = messageSource.getMessage("user.update.success", null, LocaleContextHolder.getLocale());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, message, userMapper.toDto(updatedUser));
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a user by Id", description = "Delete a user by their ID", tags = { "User" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content) })
    @DeleteMapping("/{id}")
    public ApiResponseDTO<Void> deleteUser(
            @PathVariable Long id) {
        userService.deleteUser(id);
        String message = messageSource.getMessage("user.delete.success", null, LocaleContextHolder.getLocale());
        return new ApiResponseDTO<>(HttpServletResponse.SC_NO_CONTENT, message, null);
    }

    @GetMapping("/options/physical-structures")
    @Operation(summary = "Get physical structure options")
    public ApiResponseDTO<List<OptionResponseDTO>> getPhysicalStructures() {
        List<OptionResponseDTO> options = Arrays.stream(PhysicalStructure.values())
                .map(structure -> new OptionResponseDTO(structure.name(), structure.getDescription()))
                .collect(Collectors.toList());
        return new ApiResponseDTO<>(HttpServletResponse.SC_OK, null, options);
    }
}
