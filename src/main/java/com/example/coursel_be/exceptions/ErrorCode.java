package com.example.coursel_be.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public enum ErrorCode {
    UNCATEGORIZED(500, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(404, "User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS(409, "User already exists", HttpStatus.CONFLICT),
    EMAIL_ALREADY_EXISTS(409, "Email already exists", HttpStatus.CONFLICT),
    INVALID_CREDENTIALS(401, "Invalid credentials", HttpStatus.UNAUTHORIZED),
    INVALID_REQUEST(400, "Invalid request", HttpStatus.BAD_REQUEST),
    PASS_USERNAME_INCORRECT(400, "Username and password is not incorrect", HttpStatus.BAD_REQUEST),
    COURSE_NOT_FOUND(404, "Course not found", HttpStatus.NOT_FOUND),
    COURSE_ALREADY_EXISTS(409, "Course already exists", HttpStatus.CONFLICT),
    COURSE_SAVE_ERROR(500, "Error saving course", HttpStatus.INTERNAL_SERVER_ERROR),
    CHAPTER_NOT_FOUND(404, "Chapter not found", HttpStatus.NOT_FOUND),
    CHAPTER_ALREADY_EXISTS(409, "Chapter already exists", HttpStatus.CONFLICT),
    LESSON_NOT_FOUND(404, "Lesson not found", HttpStatus.NOT_FOUND),
    ;


    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
