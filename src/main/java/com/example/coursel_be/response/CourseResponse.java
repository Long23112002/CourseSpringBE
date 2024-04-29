package com.example.coursel_be.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

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

    private Date createdAt;

    private String createBy;

    private Date updateAt;

    private String updateBy;

    private Boolean deleted;
}
