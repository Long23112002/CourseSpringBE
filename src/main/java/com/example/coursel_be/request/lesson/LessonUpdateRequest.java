package com.example.coursel_be.request.lesson;

import com.example.coursel_be.infrastructure.constant.EntityProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonUpdateRequest {

    @NotNull(message = "ID User is required")
    private Long idUserCreate;

    @NotNull(message = "ID Lesson is required")
    private Long idLesson;

    private Long idChapter;

    @NotBlank(message = "Title is not empty")
    @Size(min = EntityProperties.MAX_LENGTH_5, max = EntityProperties.MAX_LENGTH_100, message = "Title must be between 5 and 100 characters")
    private String title;

    @NotBlank(message = "Content is not empty")
    @Size(min = EntityProperties.MAX_LENGTH_5, max = EntityProperties.MAX_LENGTH_225, message = "Content must be at least 5 characters")
    private String content;

    private String videoUrl;

    private Integer lessonSequence;

}
