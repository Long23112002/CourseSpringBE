package com.example.coursel_be.request.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequest {

    private long idUser;

    private String title;

    private String content;

    private String cover;

    private Boolean deleted;
}
