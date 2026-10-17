package co.edu.unicauca.exam.service;

import co.edu.unicauca.exam.model.Exam;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExamService {

    private final ConcurrentHashMap<String, Exam> examRepository = new ConcurrentHashMap<>();

    public Exam generateExam(Exam exam) {
        String examId = UUID.randomUUID().toString();
        exam.setId(examId);
        examRepository.put(examId, exam);
        
        System.out.println("[EXAM SERVICE] Examen generado exitosamente: " + exam.getTitle() + " con " + exam.getQuestionIds().size() + " preguntas.");
        return exam;
    }

    public Exam getExamById(String id) {
        return examRepository.get(id);
    }

    public List<Exam> getAllExams() {
        return List.copyOf(examRepository.values());
    }
}