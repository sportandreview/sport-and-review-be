package it.sportandreview.admin_user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import it.sportandreview.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<AdminUserDTO> update(@Parameter(name = "adminUserDTO") @RequestBody AdminUserDTO adminUserDTO){
        return new ResponseEntity<>(service.update(adminUserDTO), HttpStatus.OK);
    }

    @PutMapping("/profileImage/{adminUserId}/{profileImage}")
    @Operation(summary = "Update profile image")
    public ResponseEntity<AdminUserDTO> updateProfileImage(@PathVariable Long adminUserId, @PathVariable String profileImage){
        return new ResponseEntity<>(service.updateProfileImage(adminUserId, profileImage), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Find admin user by id")
    public ResponseEntity<AdminUserDTO> findById(@PathVariable Long userId) {
        return new ResponseEntity<>(service.findById(userId), HttpStatus.OK);
    }

}
