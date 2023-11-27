package com.tnpserver.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student {

//    @NotEmpty(message = "UserId should not be Empty")
    @Positive(message = "UserId should be greater than zero")
    private Long userId;
    @Positive(message = "StudentId should be greater than zero")
    private Long studentId;
    private User user;
    @Size(max = 256, message = "Academic Info should be maximum 256 characters")
    private String academicInfo;
    private String resumeUrl;
    @NotEmpty(message = "Skills should not be Empty")
    @Size(max = 1024, message = "Skills should be maximum 1024 characters")
    private String skills;
    @NotEmpty(message = "Career Objectives should not be Empty")
    @Size(max = 1024, message = "Career Objectives should be maximum 1024 characters")
    private String careerObjectives;
    @Size(max = 1024, message = "Internship History should be maximum 1024 characters")
    private String internshipHistory;
    @Size(max = 1024, message = "Feedback And Ratings should be maximum 1024 characters")
    private String feedbackAndRatings;
}
