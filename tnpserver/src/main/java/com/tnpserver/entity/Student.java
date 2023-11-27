package com.tnpserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String academicInfo;
    private String resumeUrl;
    private String skills;
    private String careerObjectives;
    private String internshipHistory;
    private String feedbackAndRatings;

    // Getters and setters
}
