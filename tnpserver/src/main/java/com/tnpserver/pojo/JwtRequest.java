package com.tnpserver.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class JwtRequest {

    @NotBlank(message = "Username Should not be empty")
    private String username;
    @NotBlank(message = "Password Should not be empty")
    private String password;

}