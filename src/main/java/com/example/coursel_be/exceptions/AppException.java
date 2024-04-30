package com.example.coursel_be.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
}
