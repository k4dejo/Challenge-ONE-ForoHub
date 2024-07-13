package com.foroHub.foroHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopicDTO {

    @NotBlank(message = "El título es obligatorio")
    private String title;

    @NotBlank(message = "El mensaje es obligatorio")
    private String message;

    @NotNull(message = "El ID del autor es obligatorio")
    private Long authorId;

    @NotNull(message = "El ID del curso es obligatorio")
    private Long courseId;

    // Getters y Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
