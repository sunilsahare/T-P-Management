package com.tnpserver.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CompanyReview {

    private Long reviewId;
    private Employer employer;
    private Student student;
    private int rating;
    private String comment;
    private LocalDateTime reviewDate;
}
