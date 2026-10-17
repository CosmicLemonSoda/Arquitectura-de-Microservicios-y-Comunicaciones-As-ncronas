package co.edu.unicauca.question.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String id;
    private String authorUsername;
    private String context;
    private String statement;
    private List<String> options;
    private String correctAnswer;
    private String justification;
    private String bibliography;
    private String competence;
    private String topic;
    private String subtopic;
    private String difficulty;
    private String status; // DRAFT, PENDING_REVIEW, APPROVED, REJECTED
}