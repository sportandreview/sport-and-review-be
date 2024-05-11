package it.sportandreview.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.admin_user.AdminUserDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<User> update(@Parameter(name = "playerUserDTO") @RequestBody PlayerUserDTO playerUserDTO){
        return new ResponseEntity<>(service.update(playerUserDTO), HttpStatus.OK);
    }

    @PutMapping("/update/admin")
    @Operation(summary = "Update user")
    public ResponseEntity<User> update(@Parameter(name = "adminUserDTO") @RequestBody AdminUserDTO adminUserDTO){
        return new ResponseEntity<>(service.update(adminUserDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find user by id")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping("/updatePassword")
    @Operation(summary = "Update password")
    public ResponseEntity<UserDTO> updatePassword(@RequestBody PasswordRequest request ) {
        return new ResponseEntity<>(service.updatePassword(request), HttpStatus.OK);
    }
}
