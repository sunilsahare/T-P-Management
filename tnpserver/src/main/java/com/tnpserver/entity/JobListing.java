package com.tnpserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_listings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    private String title;
    private String description;
    private String requirements;
    private LocalDateTime applicationDeadline;
    private String status;

    // Getters and setters
}
