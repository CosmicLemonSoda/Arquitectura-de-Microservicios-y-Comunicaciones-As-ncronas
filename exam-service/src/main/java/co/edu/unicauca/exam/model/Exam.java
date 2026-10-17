package co.edu.unicauca.exam.model;

import java.util.List;

public class Exam {
    private String id;
    private String title;
    private String description;
    private List<String> questionIds;

    public Exam() {
    }

    public Exam(String id, String title, String description, List<String> questionIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questionIds = questionIds;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getQuestionIds() { return questionIds; }
    public void setQuestionIds(List<String> questionIds) { this.questionIds = questionIds; }
}