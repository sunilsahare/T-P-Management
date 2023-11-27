package com.tnpserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alumni")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Alumni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String mentorshipInterests;
    private String experienceAndCareerPath;
    private String mentorshipHistory;

    // Getters and setters
}
