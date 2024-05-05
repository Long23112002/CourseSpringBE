package com.example.coursel_be.request.chapter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterUpdateRequest {
    private Long idChapter;
    @NotNull(message = "Title is not empty")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;
    @NotNull(message = "Chapter sequence is required")
    private Integer chapterSequence;
    private Boolean deleted;
}
