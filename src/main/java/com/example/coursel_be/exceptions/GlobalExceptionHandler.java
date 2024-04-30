package com.example.coursel_be.exceptions;

import com.example.coursel_be.response.error.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException exception) {
        log.error("Exception: ", exception);
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<?>> handleAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(apiResponse);

    }

//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException exception) {
//       String enumKey = exception.getFieldError().getDefaultMessage();
//       ErrorCode errorCode = ErrorCode.valueOf(enumKey);
//        Map<String , Object> attributes = null;
//        try {
//            errorCode = ErrorCode.valueOf(enumKey);
//            var constraintViolation = exception.getBindingResult().getAllErrors().getFirst().unwrap(
//                    ConstraintViolationException.class);
//            );
//           attributes = constraintViolation.getConstraintDescriptor().getAttributes();
//           log.info(attributes.toString());
//        }catch (IllegalArgumentException e){
//            log.error("Invalid error code: {}", enumKey);
//            errorCode = ErrorCode.UNCATEGORIZED;
//        }
//
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setCode(errorCode.getCode());
//        apiResponse.setMessage(Objects.isNull(attributes) ? mapA: attributes.get("message").toString());
//    }
}
