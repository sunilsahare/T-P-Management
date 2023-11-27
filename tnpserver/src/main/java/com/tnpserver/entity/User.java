package com.tnpserver.entity;

import com.tnpserver.constants.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String mobile;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    private String profilePictureUrl;
    private boolean isActive;

}
