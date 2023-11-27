package com.tnpserver.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class JwtResponse {

    private String jwtToken;
    private String username;
}