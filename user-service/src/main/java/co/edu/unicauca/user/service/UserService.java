package co.edu.unicauca.user.service;

import co.edu.unicauca.user.dto.LoginResponseDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    // Simulación de validación de usuario (luego se puede conectar a base de datos)
    public LoginResponseDTO authenticate(String username, String password) {
        if ("docente".equals(username) && "12345".equals(password)) {
            return new LoginResponseDTO(true, "Autenticación exitosa", username, List.of("ROLE_AUTHOR", "ROLE_TEACHER"));
        } else if ("admin".equals(username) && "admin123".equals(password)) {
            return new LoginResponseDTO(true, "Autenticación exitosa como Administrador", username, List.of("ROLE_ADMIN"));
        }
        return new LoginResponseDTO(false, "Credenciales incorrectas", username, List.of());
    }
}