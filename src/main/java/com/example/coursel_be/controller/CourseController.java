package com.example.coursel_be.controller;

import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.request.CourseRequest;
import com.example.coursel_be.response.ApiResponse;
import com.example.coursel_be.response.CourseResponse;
import com.example.coursel_be.service.CourseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/course")
@Slf4j
public class CourseController {

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCourse(@Valid @RequestBody CourseRequest courseRequest) {
        try {
            String isSaved = courseService.saveCourse(courseRequest);

            if (isSaved != null) {
                ApiResponse<String> apiResponse = new ApiResponse<>();
                apiResponse.setCode(201);
                apiResponse.setMessage(isSaved);
                return ResponseEntity.ok(apiResponse);
            }
            return ResponseEntity.badRequest().body(isSaved);
        } catch (AppException e) {
            ApiResponse<String> errorResponse = new ApiResponse<>();
            errorResponse.setCode(e.getErrorCode().getCode());
            errorResponse.setMessage(e.getErrorCode().getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/get/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
        try {
            if (courseId != null) {
                ApiResponse<CourseResponse> apiResponse = new ApiResponse<>();
                CourseResponse course = courseService.getCourseById(courseId);
                apiResponse.setCode(200);
                apiResponse.setResult(course);
                return ResponseEntity.ok(apiResponse);
            }
            return ResponseEntity.badRequest().body("Course not found");
        } catch (AppException e) {
            ApiResponse<String> errorResponse = new ApiResponse<>();
            errorResponse.setCode(e.getErrorCode().getCode());
            errorResponse.setMessage(e.getErrorCode().getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCourses() {
        try {
            List<CourseResponse> listCourseResponses = courseService.getAllCourses();
            ApiResponse<List<CourseResponse>> apiResponse = new ApiResponse<>();
            apiResponse.setCode(200);
            apiResponse.setResult(listCourseResponses);
            return ResponseEntity.ok(apiResponse);
        } catch (AppException e) {
            ApiResponse<String> errorResponse = new ApiResponse<>();
            errorResponse.setCode(e.getErrorCode().getCode());
            errorResponse.setMessage(e.getErrorCode().getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
