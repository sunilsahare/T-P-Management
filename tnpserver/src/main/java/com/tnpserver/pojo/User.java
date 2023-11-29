package com.tnpserver.pojo;

import com.tnpserver.constants.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    private Long userId;
    @Size(min = 3, max = 60, message = "Username must be between 3 and 60 characters")
    private String username;
    private String password;
    @Size(min = 3, max = 60, message = "Full name must be between 3 and 60 characters")
    private String fullName;
    @Email(message = "Provide valid email address")
    private String email;
    private String gender;
    private String address;
    private String mobile;
    private String role;
    private String profilePictureUrl;
    private boolean isActive;

}
