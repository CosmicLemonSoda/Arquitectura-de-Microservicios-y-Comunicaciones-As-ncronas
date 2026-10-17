package co.edu.unicauca.user.controller;

import co.edu.unicauca.user.dto.LoginRequestDTO;
import co.edu.unicauca.user.dto.LoginResponseDTO;
import co.edu.unicauca.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = userService.authenticate(request.getUsername(), request.getPassword());
        
        if (response.isAuthenticated()) {
            return ResponseEntity.ok(response); // Retorna HTTP 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // Retorna HTTP 401 Unauthorized[cite: 8]
        }
    }
}