package com.tnpserver.exception;

import org.apache.el.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        LOG.error("Business Exception Occurred Message : {}, Stacktrace : ", ex.getMessage(), ex);
        HttpApiResponse apiResponse = HttpApiResponse.builder()
                .message("Validation Error Occurred")
                .status(HttpStatus.BAD_REQUEST.name())
                .timestamp(LocalDateTime.now())
                .reason("Failure")
                .build();
        setFieldErrors(ex, apiResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<HttpApiResponse> businessException(BusinessException e) {
        LOG.debug("Business Exception Occurred Message : {}, Stacktrace :{}", e.getMessage(), e);
        return new ResponseEntity<>(e.getResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<HttpApiResponse> technicalException(TechnicalException e) {
        LOG.debug("Technical Exception Occurred Message : {}, Stacktrace : {}", e.getMessage(), e);
        return new ResponseEntity<>(e.getResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpApiResponse> exception(Exception e) {
        LOG.error("Exception Occurred Message : {}, Stacktrace : {}", e.getMessage(), e);
        HttpApiResponse apiResponse = HttpApiResponse.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .timestamp(LocalDateTime.now())
                .reason("Failure")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    private static void setFieldErrors(MethodArgumentNotValidException ex, HttpApiResponse apiResponse) {
        List<FieldError> fieldErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            fieldErrors.add(
                    FieldError.builder()
                            .field(fieldError.getField())
                            .message(fieldError.getDefaultMessage())
                            .build()
            );
        });
        apiResponse.setFieldErrors(fieldErrors);
    }


}
