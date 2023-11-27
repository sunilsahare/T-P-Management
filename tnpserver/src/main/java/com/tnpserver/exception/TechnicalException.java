package com.tnpserver.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class TechnicalException extends Throwable {
    private ErrorResponse errorResponse;
    private List<ErrorResponse> errorList;

    public TechnicalException(String message) {
        super(message);
        this.errorResponse = ErrorResponse.builder()
                .message(message).status(HttpStatus.BAD_REQUEST.name())
                .timestamp(LocalDateTime.now()).build();

    }
    public TechnicalException(ErrorResponse error) {
        this.errorResponse = error;
    }

    public TechnicalException(List<ErrorResponse> errorList) {
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
