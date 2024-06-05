package it.sportandreview.admin_user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.ApiResponseDTO;
import it.sportandreview.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminUsers")
public class AdminUserController {

    private final AdminUserService service;

    private final UserService userService;

    @Autowired
    public AdminUserController(AdminUserService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PutMapping
    @Operation(summary = "Update admin user")
    public ResponseEntity<ApiResponseDTO<AdminUserDTO>> update(@Parameter(name = "adminUserDTO") @RequestBody AdminUserDTO adminUserDTO){
        ApiResponseDTO<AdminUserDTO> response = ApiResponseDTO.<AdminUserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Admin aggiornato con successo")
                .result(service.update(adminUserDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/profileImage/{adminUserId}/{profileImage}")
    @Operation(summary = "Update profile image")
    public ResponseEntity<ApiResponseDTO<AdminUserDTO>> updateProfileImage(@PathVariable Long adminUserId, @PathVariable String profileImage){
        ApiResponseDTO<AdminUserDTO> response = ApiResponseDTO.<AdminUserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Immagine del profilo aggiornata con successo")
                .result(service.updateProfileImage(adminUserId, profileImage))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Find admin user by id")
    public ResponseEntity<ApiResponseDTO<AdminUserDTO>> findById(@PathVariable Long userId) {
        ApiResponseDTO<AdminUserDTO> response = ApiResponseDTO.<AdminUserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Admin trovato per l'id specificato")
                .result(service.findById(userId))
                .build();
        return ResponseEntity.ok(response);
    }

}
