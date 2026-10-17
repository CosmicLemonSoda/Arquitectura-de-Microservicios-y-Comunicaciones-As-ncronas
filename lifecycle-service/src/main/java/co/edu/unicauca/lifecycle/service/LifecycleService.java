package co.edu.unicauca.lifecycle.service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LifecycleService {

    // Almacena el historial de cambios de estado por ID de pregunta
    private final ConcurrentHashMap<String, List<String>> historyMap = new ConcurrentHashMap<>();

    public String registerStateChange(String questionId, String newStatus) {
        String logEntry = "Pregunta [" + questionId + "] cambió al estado: " + newStatus + " a las " + LocalDateTime.now();
        
        historyMap.computeIfAbsent(questionId, k -> new ArrayList<>()).add(logEntry);
        
        System.out.println("[LIFECYCLE SERVICE] " + logEntry);
        return logEntry;
    }

    public List<String> getHistory(String questionId) {
        return historyMap.getOrDefault(questionId, List.of("No hay historial registrado para esta pregunta."));
    }
}