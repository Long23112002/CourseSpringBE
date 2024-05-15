package com.example.coursel_be.controller;

import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.request.lesson.LessonRequest;
import com.example.coursel_be.request.lesson.LessonUpdateRequest;
import com.example.coursel_be.response.error.ApiResponse;
import com.example.coursel_be.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/lesson")
@Slf4j
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/save")
    public ResponseEntity<?> saveLesson(@Valid @RequestBody LessonRequest lessonRequest) {
        try {
            String isSaved = lessonService.saveLesson(lessonRequest);
            return getResponseEntity(isSaved);
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(buildErrorResponse(e));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLesson(@Valid @RequestBody LessonUpdateRequest lessonUpdateRequest) {
        try {
            String isSaved = lessonService.updateLesson(lessonUpdateRequest);
            return getResponseEntity(isSaved);
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(buildErrorResponse(e));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getLessonByID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(lessonService.getLessonByID(id));
        } catch (AppException e) {
            return ResponseEntity.badRequest().body(buildErrorResponse(e));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    static ResponseEntity<?> getResponseEntity(String isSaved) {
        if (isSaved != null) {
            ApiResponse<String> apiResponse = new ApiResponse<>();
            apiResponse.setCode(201);
            apiResponse.setMessage(isSaved);
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.badRequest().body(isSaved);
    }

    private ApiResponse<String> buildErrorResponse(AppException e) {
        ApiResponse<String> errorResponse = new ApiResponse<>();
        errorResponse.setCode(e.getErrorCode().getCode());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

}
