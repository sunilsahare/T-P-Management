package com.tnpserver.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "partners")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String organizationName;
    private String industry;
    private String organizationDescription;
    private String organizationLogoUrl;
    private String reviewsAndRatings;

    // Getters and setters
}

