package com.example.coursel_be.response.lesson;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LessonResponse {
    private Long idLesson;
    private Long courseId;
    private String title;
    private String content;
    private String videoUrl;
    private Integer lessonSequence;
    private Boolean deleted;
}
