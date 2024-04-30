package com.example.coursel_be.response.course;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {

    private Long id;

    private String title;

    private String description;

    private BigDecimal coursePrice;

    private String cover;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdAt;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateAt;

    private String updateBy;

    private Boolean deleted;
}
