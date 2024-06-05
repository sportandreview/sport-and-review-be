package it.sportandreview.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.admin_user.AdminUserDTO;
import it.sportandreview.dto.ApiResponseDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PutMapping("/update/player")
    @Operation(summary = "Update  player user")
    public ResponseEntity<ApiResponseDTO<User>> update(@Parameter(name = "playerUserDTO") @Valid @RequestBody PlayerUserDTO playerUserDTO){
        ApiResponseDTO<User> response = ApiResponseDTO.<User>builder()
                .status(HttpServletResponse.SC_OK)
                .message("PlayerUser aggiornato con successo")
                .result(service.update(playerUserDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/admin")
    @Operation(summary = "Update user")
    public ResponseEntity<ApiResponseDTO<User>> update(@Parameter(name = "adminUserDTO") @Valid @RequestBody AdminUserDTO adminUserDTO){
        ApiResponseDTO<User> response = ApiResponseDTO.<User>builder()
                .status(HttpServletResponse.SC_OK)
                .message("AdminUser aggiornato con successo")
                .result(service.update(adminUserDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find user by id")
    public ResponseEntity<ApiResponseDTO<User>> findById(@PathVariable Long id) {
        ApiResponseDTO<User> response = ApiResponseDTO.<User>builder()
                .status(HttpServletResponse.SC_OK)
                .message("User per l'id richiesto")
                .result(service.findById(id))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePassword")
    @Operation(summary = "Update password")
    public ResponseEntity<ApiResponseDTO<UserDTO>> updatePassword( @Valid @RequestBody PasswordRequest request ) {
        ApiResponseDTO<UserDTO> response = ApiResponseDTO.<UserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Password aggiornato con successo")
                .result(service.updatePassword(request))
                .build();
        return ResponseEntity.ok(response);
    }
}
