package it.sportandreview.player_user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.dto.response.ApiResponseDTO;
import it.sportandreview.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playerUsers")
public class PlayerUserController {

    private final PlayerUserService service;

    private final UserService userService;

    @Autowired
    public PlayerUserController(PlayerUserService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PutMapping
    @Operation(summary = "Update player user")
    public ResponseEntity<ApiResponseDTO<PlayerUserDTO>> update(@Parameter(name = "playerUserDTO") @RequestBody PlayerUserDTO playerUserDTO){
        ApiResponseDTO<PlayerUserDTO> response = ApiResponseDTO.<PlayerUserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("PlayerUser aggiornato con successo")
                .result(service.update(playerUserDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/profileImage/{playerUserId}/{profileImage}")
    @Operation(summary = "Update profile image")
    public ResponseEntity<ApiResponseDTO<PlayerUserDTO>> updateProfileImage(@PathVariable Long playerUserId, @PathVariable String profileImage){
        ApiResponseDTO<PlayerUserDTO> response = ApiResponseDTO.<PlayerUserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Immagine del profilo aggiornata con successo")
                .result(service.updateProfileImage(playerUserId, profileImage))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Find player user by id")
    public ResponseEntity<ApiResponseDTO<PlayerUserDTO>> findById(@PathVariable Long userId) {
        ApiResponseDTO<PlayerUserDTO> response = ApiResponseDTO.<PlayerUserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Player user per l'id richiesto")
                .result(service.findById(userId))
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/favoriteClubs/{clubId}/{playerUserId}/{isFavorite}")
    @Operation(summary = "Add favourite clubs")
    public ResponseEntity<ApiResponseDTO<PlayerUserDTO>> updateFavoriteClub(@PathVariable Long clubId, @PathVariable Long playerUserId, @PathVariable Boolean isFavorite){
        ApiResponseDTO<PlayerUserDTO> response = ApiResponseDTO.<PlayerUserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Club preferito aggiornato con successo")
                .result(service.updateFavoriteClub(clubId, playerUserId, isFavorite))
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/favoriteFields/{fieldId}/{playerUserId}/{isFavorite}")
    @Operation(summary = "Add favourite fields")
    public ResponseEntity<ApiResponseDTO<PlayerUserDTO>> updateFavoriteField(@PathVariable Long fieldId, @PathVariable Long playerUserId, @PathVariable Boolean isFavorite){
        ApiResponseDTO<PlayerUserDTO> response = ApiResponseDTO.<PlayerUserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Field preferito aggiornato con successo")
                .result(service.updateFavoriteField(fieldId, playerUserId, isFavorite))
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/favoriteHighlights/{highlightId}/{playerUserId}/{isFavorite}")
    @Operation(summary = "Add favourite highlights")
    public ResponseEntity<ApiResponseDTO<PlayerUserDTO>> updateFavoriteHighlight (@PathVariable Long highlightId, @PathVariable Long playerUserId, @PathVariable Boolean isFavorite){
        ApiResponseDTO<PlayerUserDTO> response = ApiResponseDTO.<PlayerUserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Highlight preferito aggiornato con successo")
                .result(service.updateFavoriteHighlight(highlightId, playerUserId, isFavorite))
                .build();
        return ResponseEntity.ok(response);
    }
    @PutMapping("/friends/{preferredPlayerUserId}/{playerUserId}/{isFavorite}")
    @Operation(summary = "Add favourite friends")
    public ResponseEntity<ApiResponseDTO<PlayerUserDTO>> updateFriends(@PathVariable Long preferredPlayerUserId, @PathVariable Long playerUserId, @PathVariable Boolean isFavorite){
        ApiResponseDTO<PlayerUserDTO> response = ApiResponseDTO.<PlayerUserDTO>builder()
                .status(HttpServletResponse.SC_OK)
                .message("Friends preferito aggiornato con successo")
                .result(service.updateFriends(preferredPlayerUserId, playerUserId, isFavorite))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byNicknameContaining/{wordInserted}")
    @Operation(summary = "Find by nickname containing")
    public ResponseEntity<ApiResponseDTO<List<PlayerUserDTO>>> findByNicknameContaining(@PathVariable String wordInserted) {
        ApiResponseDTO<List<PlayerUserDTO>> response = ApiResponseDTO.<List<PlayerUserDTO>>builder()
                .status(HttpServletResponse.SC_OK)
                .message("nickname trovato in base ai carattere inseriti")
                .result(service.findByNicknameContaining(wordInserted))
                .build();
        return ResponseEntity.ok(response);
    }
}
