package co.edu.unicauca.question.dto;

import java.util.List;

public class QuestionDTO {
    private String id;
    private String context;
    private String statement;
    private List<String> options;
    private String correctAnswer;
    private String category;
    private String authorUsername;
    private String status;

    public QuestionDTO() {
    }

    public QuestionDTO(String id, String context, String statement, List<String> options, String correctAnswer, String category, String authorUsername, String status) {
        this.id = id;
        this.context = context;
        this.statement = statement;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.authorUsername = authorUsername;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getContext() { return context; }
    public void setContext(String context) { this.context = context; }

    public String getStatement() { return statement; }
    public void setStatement(String statement) { this.statement = statement; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getAuthorUsername() { return authorUsername; }
    public void setAuthorUsername(String authorUsername) { this.authorUsername = authorUsername; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}