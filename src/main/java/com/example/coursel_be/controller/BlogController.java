package com.example.coursel_be.controller;

import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.request.blog.BlogRequest;
import com.example.coursel_be.request.course.CourseRequest;
import com.example.coursel_be.response.error.ApiResponse;
import com.example.coursel_be.service.BlogService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1/blog")
public class BlogController {

    private BlogService blogService;
    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCourse(@Valid @RequestBody BlogRequest blogRequest) {
        try {
            String isSaved = blogService.saveBlog(blogRequest);

            return getResponseEntity(isSaved);
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(buildErrorResponse(e));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private ResponseEntity<?> getResponseEntity(String isUpdated) {
        if (isUpdated != null) {
            ApiResponse<String> apiResponse = new ApiResponse<>();
            apiResponse.setCode(200);
            apiResponse.setMessage(isUpdated);
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(isUpdated);
    }

    private ApiResponse<String> buildErrorResponse(AppException e) {
        ApiResponse<String> errorResponse = new ApiResponse<>();
        errorResponse.setCode(e.getErrorCode().getCode());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}
