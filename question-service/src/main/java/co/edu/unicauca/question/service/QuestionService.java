package co.edu.unicauca.question.service;

import co.edu.unicauca.question.dto.QuestionDTO;
import co.edu.unicauca.question.dto.ValidationResponseDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QuestionService {

    private final ConcurrentHashMap<String, QuestionDTO> repository = new ConcurrentHashMap<>();
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.exchange.name:saberpro_exchange}")
    private String exchangeName;

    @Value("${rabbit.routing.key:question_events}")
    private String routingKey;

    public QuestionService(RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    public QuestionDTO createQuestion(QuestionDTO question) {
        // 1. Comunicación Síncrona (HTTP): Llamar a validation-service (:8082)
        String validationUrl = "http://localhost:8082/validation/validate";
        ValidationResponseDTO validationResponse = restTemplate.postForObject(validationUrl, question, ValidationResponseDTO.class);

        if (validationResponse != null && !validationResponse.isValid()) {
            throw new IllegalArgumentException("La pregunta no superó las reglas de validación: " + validationResponse.getErrors());
        }

        String id = UUID.randomUUID().toString();
        question.setId(id);
        question.setStatus("BORRADOR");
        repository.put(id, question);

        return question;
    }

    public QuestionDTO submitQuestion(String id) {
        QuestionDTO question = repository.get(id);
        if (question == null) {
            throw new NoSuchElementException("Pregunta no encontrada con el ID: " + id);
        }

        question.setStatus("PENDIENTE_REVISION");
        repository.put(id, question);

        // 2. Comunicación Asíncrona (RabbitMQ): Publicar evento para notification-service
        Map<String, Object> event = new HashMap<>();
        event.put("eventId", UUID.randomUUID().toString());
        event.put("questionId", question.getId());
        event.put("authorUsername", question.getAuthorUsername());
        event.put("status", question.getStatus());
        event.put("message", "La pregunta fue enviada exitosamente a revisión.");

        try {
            rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
            System.out.println("[QUESTION SERVICE] Evento enviado a RabbitMQ para la pregunta ID: " + id);
        } catch (Exception e) {
            System.err.println("[QUESTION SERVICE] Error al enviar evento a RabbitMQ: " + e.getMessage());
        }

        return question;
    }

    public QuestionDTO getQuestionById(String id) {
        return repository.get(id);
    }

    public List<QuestionDTO> getAllQuestions() {
        return new ArrayList<>(repository.values());
    }
}