package com.example.coursel_be.request.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseUpdateRequest {

    private Long idCourse;

    @NotNull(message = "Id user create is not empty")
    private Long idUserUpdate;

    @Size(min = 10, max = 50, message = "Title must be between 5 and 50 characters")
    @NotBlank(message = "Title is not empty")
    private String title;

    @Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    @NotBlank(message = "Description is not empty")
    private String description;

    @PositiveOrZero(message = "Price must be non-negative")
    private BigDecimal coursePrice;

    private String cover;

    private Boolean deleted;

}
