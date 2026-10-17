package co.edu.unicauca.validation.controller;

import co.edu.unicauca.validation.dto.ValidationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    @PostMapping("/validate")
    public ResponseEntity<ValidationResponseDTO> validateQuestion(@RequestBody Map<String, Object> questionData) {
        List<String> errors = new ArrayList<>();
        
        // Reglas de validación básicas de ejemplo
        String statement = (String) questionData.get("statement");
        if (statement == null || statement.trim().isEmpty()) {
            errors.add("El enunciado de la pregunta no puede estar vacío.");
        }

        boolean isValid = errors.isEmpty();
        ValidationResponseDTO response = new ValidationResponseDTO(isValid, errors);
        
        return ResponseEntity.ok(response);
    }
}