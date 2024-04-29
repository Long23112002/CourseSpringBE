package com.example.coursel_be.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {

    private Long idCourse;

    @NotBlank(message = "Id user create is not empty")
    private Long idUserCreate;

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
