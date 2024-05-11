package it.sportandreview.player_user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PlayerUserDTO> update(@Parameter(name = "playerUserDTO") @RequestBody PlayerUserDTO playerUserDTO){
        return new ResponseEntity<>(service.update(playerUserDTO), HttpStatus.OK);
    }

    @PutMapping("/profileImage/{playerUserId}/{profileImage}")
    @Operation(summary = "Update profile image")
    public ResponseEntity<PlayerUserDTO> updateProfileImage(@PathVariable Long playerUserId, @PathVariable String profileImage){
        return new ResponseEntity<>(service.updateProfileImage(playerUserId, profileImage), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Find player user by id")
    public ResponseEntity<PlayerUserDTO> findById(@PathVariable Long userId) {
        return new ResponseEntity<>(service.findById(userId), HttpStatus.OK);
    }

    @PutMapping("/favoriteClubs/{clubId}/{playerUserId}/{isFavorite}")
    @Operation(summary = "Add favourite clubs")
    public ResponseEntity<PlayerUserDTO> updateFavoriteClub(@PathVariable Long clubId, @PathVariable Long playerUserId, @PathVariable Boolean isFavorite){
        return new ResponseEntity<>(service.updateFavoriteClub(clubId, playerUserId, isFavorite), HttpStatus.OK);
    }
    @PutMapping("/favoriteFields/{fieldId}/{playerUserId}/{isFavorite}")
    @Operation(summary = "Add favourite fields")
    public ResponseEntity<PlayerUserDTO> updateFavoriteField(@PathVariable Long fieldId, @PathVariable Long playerUserId, @PathVariable Boolean isFavorite){
        return new ResponseEntity<>(service.updateFavoriteField(fieldId, playerUserId, isFavorite), HttpStatus.OK);
    }
    @PutMapping("/favoriteHighlights/{highlightId}/{playerUserId}/{isFavorite}")
    @Operation(summary = "Add favourite highlights")
    public ResponseEntity<PlayerUserDTO> updateFavoriteHighlight (@PathVariable Long highlightId, @PathVariable Long playerUserId, @PathVariable Boolean isFavorite){
        return new ResponseEntity<>(service.updateFavoriteHighlight(highlightId, playerUserId, isFavorite), HttpStatus.OK);
    }
    @PutMapping("/friends/{preferredPlayerUserId}/{playerUserId}/{isFavorite}")
    @Operation(summary = "Add favourite friends")
    public ResponseEntity<PlayerUserDTO> updateFriends(@PathVariable Long preferredPlayerUserId, @PathVariable Long playerUserId, @PathVariable Boolean isFavorite){
        return new ResponseEntity<>(service.updateFriends(preferredPlayerUserId, playerUserId, isFavorite), HttpStatus.OK);
    }

    @GetMapping("/byNicknameContaining/{wordInserted}")
    @Operation(summary = "Find by nickname containing")
    public ResponseEntity<List<PlayerUserDTO>> findByNicknameContaining(@PathVariable String wordInserted) {
        return new ResponseEntity<>(service.findByNicknameContaining(wordInserted), HttpStatus.OK);
    }
}
