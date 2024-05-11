package it.sportandreview.auth;

import io.swagger.v3.oas.annotations.Operation;
import it.sportandreview.admin_user.AdminUserDTO;
import it.sportandreview.player_user.PlayerUserDTO;
import it.sportandreview.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register/player")
    @Operation(summary = "Registration new player user")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody PlayerUserDTO playerUserDTO) {
        return ResponseEntity.ok(service.register(playerUserDTO, Role.USER));
    }

    @PostMapping("/register/admin")
    @Operation(summary = "Registration new admin user")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AdminUserDTO adminUserDTO) {
        return ResponseEntity.ok(service.register(adminUserDTO, Role.ADMIN));
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate user")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PutMapping("/refresh")
    @Operation(summary = "Update token user")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.refreshToken(request));
    }

}
