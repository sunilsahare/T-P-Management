package com.tnpserver.exception;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
@ToString
public class ErrorResponse {

    private String message;
    private String status;
    private String reason;
    private LocalDateTime timestamp;

}
