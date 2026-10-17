package co.edu.unicauca.review.controller;

import co.edu.unicauca.review.model.Review;
import co.edu.unicauca.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review created = reviewService.submitReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Review>> getReviewsByQuestion(@PathVariable String questionId) {
        return ResponseEntity.ok(reviewService.getReviewsByQuestion(questionId));
    }
}