package com.tnpserver.pojo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Admin {
    private Long userId;
    private User user;
    private String accessLevel;
    private String feedbackAndSupportManagement;
    private String systemConfiguration;
}
