package com.tnpserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long employerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String companyName;
    private String industry;
    private String companyDescription;
    private String companyLogoUrl;
    private String reviewsAndRatings;

    // Getters and setters
}

