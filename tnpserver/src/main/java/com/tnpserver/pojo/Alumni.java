package com.tnpserver.pojo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Alumni {

    private Long userId;
    private User user;
    private String mentorshipInterests;
    private String experienceAndCareerPath;
    private String mentorshipHistory;
}
