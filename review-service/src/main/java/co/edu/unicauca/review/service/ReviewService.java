package co.edu.unicauca.review.service;

import co.edu.unicauca.review.model.Review;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ConcurrentHashMap<String, Review> reviewRepository = new ConcurrentHashMap<>();

    public Review submitReview(Review review) {
        String reviewId = UUID.randomUUID().toString();
        review.setId(reviewId);
        reviewRepository.put(reviewId, review);
        
        System.out.println("[REVIEW SERVICE] Evaluación registrada para la pregunta: " + review.getQuestionId() + " con decisión: " + review.getDecision());
        return review;
    }

    public List<Review> getReviewsByQuestion(String questionId) {
        return reviewRepository.values().stream()
                .filter(r -> r.getQuestionId().equalsIgnoreCase(questionId))
                .collect(Collectors.toList());
    }
}