package co.edu.unicauca.lifecycle.controller;

import co.edu.unicauca.lifecycle.service.LifecycleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lifecycle")
public class LifecycleController {

    private final LifecycleService lifecycleService;

    public LifecycleController(LifecycleService lifecycleService) {
        this.lifecycleService = lifecycleService;
    }

    @PostMapping("/transition")
    public ResponseEntity<String> transitionState(@RequestParam String questionId, @RequestParam String status) {
        String response = lifecycleService.registerStateChange(questionId, status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{questionId}")
    public ResponseEntity<List<String>> getHistory(@PathVariable String questionId) {
        return ResponseEntity.ok(lifecycleService.getHistory(questionId));
    }
}