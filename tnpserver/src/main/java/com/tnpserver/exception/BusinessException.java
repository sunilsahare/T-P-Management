package com.tnpserver.exception;


import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class BusinessException extends RuntimeException {

    private ErrorResponse errorResponse;
    private List<ErrorResponse> errorList;

    public BusinessException(String message) {
        super(message);
        this.errorResponse = ErrorResponse.builder()
                .message(message).status(HttpStatus.BAD_REQUEST.name())
                .reason("FAILURE")
                .timestamp(LocalDateTime.now()).build();

    }
    public BusinessException(ErrorResponse error) {
        this.errorResponse = error;
    }

    public BusinessException(List<ErrorResponse> errorList) {
        this.errorList.addAll(errorList);
    }

    public HttpApiResponse getResponse() {
       return HttpApiResponse.builder()
                .message(errorResponse.getMessage())
                .status(errorResponse.getStatus())
                .timestamp(errorResponse.getTimestamp())
                .reason("FAILURE")
                .errorList(this.errorList)
                .fieldErrors(null)
                .build();
    }

}
