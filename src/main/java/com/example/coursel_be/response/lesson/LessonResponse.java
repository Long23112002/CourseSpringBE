package com.example.coursel_be.response.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponse {
    private Long idLesson;
    private Long courseId;
    private String title;
    private String content;
    private String videoUrl;
    private Integer lessonSequence;
    private Boolean deleted;
}
