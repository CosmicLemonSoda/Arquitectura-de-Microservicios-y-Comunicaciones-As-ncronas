package co.edu.unicauca.validation.service;

import co.edu.unicauca.validation.dto.ValidationRequestDTO;
import co.edu.unicauca.validation.dto.ValidationResponseDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {

    public ValidationResponseDTO validateQuestion(ValidationRequestDTO request) {
        List<String> errors = new ArrayList<>();

        // Regla 1: Debe tener al menos 4 opciones
        if (request.getOptions() == null || request.getOptions().size() < 4) {
            errors.add("La pregunta debe tener exactamente 4 opciones/distractores.");
        }

        // Regla 2: Ninguna opción debe ser "Todas las anteriores" o "Ninguna de las anteriores"
        if (request.getOptions() != null) {
            for (String option : request.getOptions()) {
                String lower = option.toLowerCase();
                if (lower.contains("todas las anteriores") || lower.contains("ninguna de las anteriores")) {
                    errors.add("No se permiten opciones del tipo 'Todas las anteriores' o 'Ninguna de las anteriores'.");
                    break;
                }
            }
        }

        // Regla 3: El contexto y el enunciado no pueden estar vacíos
        if (request.getContext() == null || request.getContext().trim().isEmpty()) {
            errors.add("El contexto de la pregunta es obligatorio.");
        }
        if (request.getStatement() == null || request.getStatement().trim().isEmpty()) {
            errors.add("El enunciado de la pregunta es obligatorio.");
        }

        boolean isValid = errors.isEmpty();
        return new ValidationResponseDTO(isValid, errors);
    }
}