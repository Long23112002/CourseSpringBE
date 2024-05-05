package com.example.coursel_be.response.course;

import com.example.coursel_be.entity.Chapter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {

    private Long id;

    private String title;

    private String description;

    private BigDecimal coursePrice;

    private String cover;

    private Date createdAt;

    private String createBy;

    private Date updateAt;

    private String updateBy;

    private List<Chapter> listChapter;

    private Boolean deleted;
}
