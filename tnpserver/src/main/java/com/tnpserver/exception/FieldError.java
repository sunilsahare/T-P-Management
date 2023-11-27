package com.tnpserver.exception;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class FieldError {

    private String field;
    private String message;
    private String reason;

}
