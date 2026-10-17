package co.edu.unicauca.review.model;

public class Review {
    private String id;
    private String questionId;
    private String reviewerUsername;
    private String comments;
    private String decision; // APROBADA, RECHAZADA, NECESITA_MEJORAS

    public Review() {
    }

    public Review(String id, String questionId, String reviewerUsername, String comments, String decision) {
        this.id = id;
        this.questionId = questionId;
        this.reviewerUsername = reviewerUsername;
        this.comments = comments;
        this.decision = decision;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getQuestionId() { return questionId; }
    public void setQuestionId(String questionId) { this.questionId = questionId; }

    public String getReviewerUsername() { return reviewerUsername; }
    public void setReviewerUsername(String reviewerUsername) { this.reviewerUsername = reviewerUsername; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }
}